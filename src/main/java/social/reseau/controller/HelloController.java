package social.reseau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import social.reseau.users.model.User;
import social.reseau.users.service.UserService;

@Controller
@RequestMapping("/hello")
//@PreAuthorize("isAuthenticated()")
public class HelloController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getHello(Model model) {
        //model.addAttribute("username", getAuthenticatedUsername());
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        return "hello";
    }

    private String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

}
