package social.reseau.users.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import social.reseau.security.model.UserPrincipal;
import social.reseau.users.model.User;

import java.util.List;

@Service
public class UserService {

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = null;
        if(auth.getPrincipal() instanceof UserPrincipal) {
            userPrincipal = (UserPrincipal) auth.getPrincipal();
        }
        if(userPrincipal == null) {
            return null;
        }
        return userPrincipal.getUser();
    }

    public List<User> getAll() {
        //ToDo
        return null;
    }

    public void save(User user) {
        //ToDo
    }
}
