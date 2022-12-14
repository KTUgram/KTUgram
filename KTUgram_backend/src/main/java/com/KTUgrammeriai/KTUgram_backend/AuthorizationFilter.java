package com.KTUgrammeriai.KTUgram_backend;

import com.KTUgrammeriai.KTUgram_backend.authentification.Session;
import com.KTUgrammeriai.KTUgram_backend.authentification.SessionHandlerService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private static final List<String> LOCAL_APIS = Arrays.asList(
                    new String[] {"/user/re-login", "/user/login", "/user/logout", "/user/register", "/translations", "/user/get-access-token", "/v1", "/actuator/prometheus", "/user/check-username"});

    private SessionHandlerService sessionHandlerService;

    public AuthorizationFilter(final AuthenticationManager authManager, final SessionHandlerService sessionHandlerService) {
        super(authManager);
        this.sessionHandlerService = sessionHandlerService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse res, final FilterChain chain)
                    throws IOException, ServletException {

        if (LOCAL_APIS.contains(request.getServletPath())) {
            chain.doFilter(request, res);
            return;
        }

        String sessionKey = null;
        boolean proceed = false;
        if (request.getCookies() != null) {
            for (final javax.servlet.http.Cookie cookie : request.getCookies()) {
                if ("refreshtoken".equals(cookie.getName())) {
                    sessionKey = cookie.getValue();
                    break;
                }
            }
        } else {
            System.out.println("No cookies for request: " + request.getPathInfo());
        }
        if (sessionKey != null) {
            final Session session = sessionHandlerService.getSession(sessionKey);
            if (session != null) {
                CurrentUserImpl.setUser(session.getId() + "", Arrays.asList(session.getPermissions().split(",")));

                proceed = true;
            }
        }

        if (!proceed) {
            SecurityContextHolder.clearContext();
            System.out.println("Failed to process authentication request");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
            return;
        }

        chain.doFilter(request, res);
    }

    /*
     * private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
     * final String permissions = request.getHeader(HEADER_STRING); final String userId =
     * request.getHeader(USER_ID);
     *
     * if ((permissions != null) && !permissions.isEmpty() && (userId != null) && !userId.isEmpty()) {
     * return new UsernamePasswordAuthenticationToken(userId, null, getAuthorities(permissions)); }
     * return null; }
     *
     * private List<GrantedAuthority> getAuthorities(final String permissions) { final String p[] =
     * permissions.split(","); return Arrays.asList(p).stream().map(item -> new
     * SimpleGrantedAuthority(item)).collect(Collectors.toList()); }
     */
}
