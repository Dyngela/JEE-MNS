package social.reseau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.reseau.security.dto.LoginRequestDTO;
import social.reseau.security.dto.RegisterRequestDTO;
import social.reseau.users.model.Role;
import social.reseau.users.model.User;
import social.reseau.users.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO register) {
        User user = new User();
        user.setEmail(register.getEmail());
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.getRoles().add(Role.USER);
        userService.save(user);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(register.getUsername(),
                        register.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.created(null).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO login) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

}
