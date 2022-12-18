package com.KTUgrammeriai.KTUgram_backend.authentification;

import com.KTUgrammeriai.KTUgram_backend.admin.AdminService;
import com.KTUgrammeriai.KTUgram_backend.email.EmailDetails;
import com.KTUgrammeriai.KTUgram_backend.email.EmailService;
import com.KTUgrammeriai.KTUgram_backend.person.Person;
import com.KTUgrammeriai.KTUgram_backend.person.PersonService;
import com.KTUgrammeriai.KTUgram_backend.user.RegisterUserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.User;
import com.KTUgrammeriai.KTUgram_backend.user.UserDTO;
import com.KTUgrammeriai.KTUgram_backend.user.UserService;
import com.KTUgrammeriai.KTUgram_backend.utils.FileUploadUtils;
import com.KTUgrammeriai.KTUgram_backend.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private PersonService personService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @PostMapping(value = "/user/register")
    public ResponseEntity<TokenResponse> register(@Param("profile_pic") MultipartFile pic, @RequestParam("register_data") String data) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        RegisterUserDTO user = objectMapper.readValue(data, RegisterUserDTO.class);
        System.out.println(user.getPerson().getUsername());

        Person existingPerson = personService.getPersonByUsername(user.getPerson().getUsername());
        if(existingPerson != null){
            System.out.println("user already exists");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String uploadDir = null;

        if(pic != null){
            String extension = pic.getOriginalFilename().split("\\.")[1];
            if(extension != null) {
                String fileName = RandomString.make(20) + "." + pic.getOriginalFilename().split("\\.")[1];
                uploadDir = "images/profile_pics/";
                FileUploadUtils.saveFile(uploadDir, fileName, pic);
                uploadDir += fileName;
            }
        }


        Person newPerson = new Person();
        User newUser = new User();
        newPerson.setUsername(user.getPerson().getUsername());
        newPerson.setEmail(user.getPerson().getEmail());
        newPerson.setPassword(passwordEncoder.encode(user.getPerson().getPassword()));
        newPerson.setName(user.getPerson().getName());
        newPerson.setSurname(user.getPerson().getSurname());
        Person savedPerson = personService.personRepository.save(newPerson);
        newUser.setPerson(savedPerson);
        newUser.setAbout(user.getAbout());
        newUser.setProfile_pic(uploadDir);
        newUser.setStatus(1);
        newUser.setState(1);
        newUser.setState(3);
        newUser.setConfirm(RandomString.make(8));
        EmailDetails email = new EmailDetails();
        email.setRecipient(user.getPerson().getEmail());
        email.setMsgBody(String.format("Hello %s %s, \nThank you for registering to KTUGram! \nYour confirmation code is: %s", user.getPerson().getName(), user.getPerson().getSurname(), newUser.getConfirm()));
        email.setSubject("KTUGram Account Confirmation");
        emailService.sendSimpleMail(email);
        userService.userRepository.save(newUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/login", consumes = {"application/json"})
    public ResponseEntity<TokenResponse> login(@RequestBody final TokenRequest tokenRequest) {
        Person person = personService.getPersonByUsername(tokenRequest.getUsername());
        if (person == null) {
            System.out.println("User not found by " + tokenRequest.getUsername());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean passwordsMatched = false;
        final String oldPassword = personService.getPassword(person.getId());
        passwordsMatched = checkPassword(tokenRequest.getPassword(), oldPassword, tokenRequest.getUsername());

        if (passwordsMatched) {

            final long personId = person.getId();
            boolean isAdmin = adminService.findByPersonId(personId) != null;

            final TokenResponse response = createResponse(jwtTokenUtil.generateToken(tokenRequest), getRights(personId, isAdmin), person, isAdmin);

            if(!isAdmin) //check if user is blocked
            {
                int status = userService.userRepository.findByPerson_Id(personId).getStatus();
                int state = userService.userRepository.findByPerson_Id(personId).getState();
                if(status == 2) //blocked
                {
                    System.out.println("Access attempt from a blocker user  " + tokenRequest.getUsername());
                    return new ResponseEntity<>(HttpStatus.valueOf(202));
                }
                if(state == 3){
                    System.out.println("Access attempt from an unconfirmed user  " + tokenRequest.getUsername());
                    return new ResponseEntity<>(HttpStatus.valueOf(203));
                }
                if(state == 2){
                    System.out.println("Access attempt from a deleted user  " + tokenRequest.getUsername());
                    return new ResponseEntity<>(HttpStatus.valueOf(204));
                }
            }
            // create a cookie
            final String refreshToken = UUID.randomUUID().toString();
            sessionHandlerService.registerSession(refreshToken, personId, String.join(",", response.getRights()));

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

    @PostMapping(value = "/user/confirm")
    public ResponseEntity<Void> confirmRegistration(@RequestBody String code){
        Optional<User> user = userService.getUserByConfirmationCode(code);
        System.out.println(code);

        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        User _user = user.get();
        _user.setConfirm(null);
        _user.setState(1);
        userService.userRepository.save(_user);
        return new ResponseEntity<>(HttpStatus.OK);
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
                    createResponse(jwtTokenUtil.generateToken(token), Arrays.asList(session.getPermissions().split(",")), null, false);
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

    private TokenResponse createResponse(final String token, final List<String> rights, final Person person, final boolean isAdmin) {
        final TokenResponse response = new TokenResponse();
        response.setToken(token);
        response.setRights(rights);
        response.setSettings(getSettings());
        response.setLoggedInUserName(person.getUsername());
        response.setLoggedInUserId(person.getId());
        response.setAdmin(isAdmin);

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

    @PostMapping(value = "/admin/all-users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> allUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user: users) {
            usersDTO.add(Utils.convertUser(user));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/user/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam("username") String username){
        System.out.println(username);
        System.out.println(userService.userExists(username));
        return new ResponseEntity<>(userService.userExists(username), HttpStatus.OK);
    }

    private List<String> getRights(final long employeeId, final boolean isAdmin) {
        return isAdmin ? List.of("ADMIN") : List.of("USER");
    }
}
