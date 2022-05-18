package social.reseau.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import social.reseau.security.dto.LoginRequestDTO;
import social.reseau.security.dto.RegisterRequestDTO;
import social.reseau.users.model.Gender;
import social.reseau.users.model.Role;
import social.reseau.users.model.User;
import social.reseau.users.service.UserService;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String register(final Model model) {
        model.addAttribute("userData", new RegisterRequestDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("userData") RegisterRequestDTO register, final Model model) {
        try {
            User user = new User();
            user.setFirstName(register.getFirstName());
            user.setLastName(register.getLastName());
            user.setEmail(register.getEmail());
            user.setUsername(register.getUsername());
            user.setPassword(passwordEncoder.encode(register.getPassword()));
            user.setGender(Gender.OTHER);
            user.getRoles().add(Role.USER);
            userService.create(user);
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            register.getUsername(),
                            register.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            //model.addAttribute("registrationForm", register);
            return "/auth/signup";
        }
        return "redirect:/hello";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO login) {
        //System.out.println(login.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok().build();
    }

}
