package com.KTUgrammeriai.KTUgram_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CurrentUserImpl {
    private static Logger LOG = LoggerFactory.getLogger(CurrentUserImpl.class);

    public long getId() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();

        final String principal = authentication.getPrincipal().toString();
        if ("anonymousUser".equals(principal)) {
            return 0;
        }

        return Long.valueOf(principal);
    }

    public Authentication getAuthentication() {
        final SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

    public void setAuthentication(final Authentication authentication) {
        final SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }

    public static void setUser(final String userId, final List<String> permissions) {
        UsernamePasswordAuthenticationToken authentication = null;
        if ((permissions != null) && !permissions.isEmpty() && (userId != null) && !userId.isEmpty()) {
            authentication = new UsernamePasswordAuthenticationToken(userId, null, getAuthorities(permissions));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static List<GrantedAuthority> getAuthorities(final List<String> permissions) {
        if (permissions == null) {
            return Collections.emptyList();
        }
        return permissions.stream().map(item -> new SimpleGrantedAuthority(StringUtils.hasLength(item) ? item : "-"))
                        .collect(Collectors.toList());
    }

}
