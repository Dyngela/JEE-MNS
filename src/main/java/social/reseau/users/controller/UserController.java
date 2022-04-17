package social.reseau.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import social.reseau.users.model.User;
import social.reseau.users.service.UserService;

import java.util.List;

@RestController("/users")
@PreAuthorize("hasRole('USER','ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getMe(){
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

}
