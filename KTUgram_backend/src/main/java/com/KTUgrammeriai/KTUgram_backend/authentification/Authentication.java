package com.KTUgrammeriai.KTUgram_backend.authentification;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Authentication {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SessionHandlerService sessionHandlerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register", consumes = {"application/json"})
    public ResponseEntity<TokenResponse> register(@RequestBody final UserDTO user){
        User existingUser = userService.userRepository.findByUsername(user.getUsername());
        if(existingUser != null){
            System.out.println("user already exists");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/login", consumes = {"application/json"})
    public ResponseEntity<TokenResponse> login(@RequestBody final TokenRequest tokenRequest) {
        User user = userService.userRepository.findByUsername(tokenRequest.getUsername());
        if (user == null) {
            System.out.println("User not found by " + tokenRequest.getUsername());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean passwordsMatched = false;
        final String oldPassword = userService.getPassword(user.getId());
        passwordsMatched = checkPassword(tokenRequest.getPassword(), oldPassword, tokenRequest.getUsername());

        if (passwordsMatched) {
            final long userId = user.getId();

            final TokenResponse response = createResponse(jwtTokenUtil.generateToken(tokenRequest), getRights(userId), user);
            if (response == null) {
                System.out.println("User " + tokenRequest.getUsername() + "doesn't have any rights assigned.");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // create a cookie
            final String refreshToken = UUID.randomUUID().toString();
            sessionHandlerService.registerSession(refreshToken, userId, String.join(",", response.getRights()));

            final ResponseCookie cookie = ResponseCookie.from("refreshtoken", refreshToken).maxAge(24 * 60 * 60 * 7).httpOnly(true)
                    .secure(false).path("/").build();

            final HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

            return new ResponseEntity<TokenResponse>(response, headers, HttpStatus.OK);
        } else {
            System.out.println("Incorrect password entered for the user " + tokenRequest.getUsername());
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private HashMap<String, Object> getSettings() {
        return new HashMap();
    }

    @PostMapping(value = "/user/get-access-token", consumes = {"application/json"})
    public ResponseEntity<TokenResponse> readCookie(@CookieValue(value = "refreshtoken") final String token) {

        if (sessionHandlerService.hasSession(token)) {
            final TokenResponse response = new TokenResponse();
            response.setToken(jwtTokenUtil.generateToken(token));
            response.setRights(null);
            response.setSettings(getSettings());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/user/re-login", consumes = {"application/json"})
    public ResponseEntity<TokenResponse> reLogin(@CookieValue(value = "refreshtoken") final String token) {

        if (sessionHandlerService.hasSession(token)) {
            final Session session = sessionHandlerService.getSession(token);
            final TokenResponse response =
                    createResponse(jwtTokenUtil.generateToken(token), Arrays.asList(session.getPermissions().split(",")), null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean checkPassword(final String enteredPassword, final String oldPassword, final String loginName) {
        try {
            return passwordEncoder.matches(enteredPassword, oldPassword);
        } catch (final Exception e) {
            System.out.printf("Passwords doesn't match for the user: %s. Error: %s%n", loginName, e.getMessage());
        }
        return false;
    }

    private TokenResponse createResponse(final String token, final List<String> rights, final User user) {
        /*
         * if (rights.isEmpty()) { return null; }
         */

        final TokenResponse response = new TokenResponse();
        response.setToken(token);
        response.setRights(rights);
        response.setSettings(getSettings());
        response.setLoggedInUserName(user.getUsername());
        response.setLoggedInUserId(user.getId());

        return response;
    }

    @PostMapping(value = "/user/logout", consumes = {"application/json"})
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshtoken") final String token) {
        if (sessionHandlerService.hasSession(token)) {
            sessionHandlerService.unregisterSession(token);
            System.out.printf("User with token '%s' is logged out. Has session reponse: %b%n", token,
                    sessionHandlerService.hasSession(token));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<String> getRights(final long employeeId) {
        //return userService.getEmployeeRights(employeeId);
        return new ArrayList<>();
    }
}
