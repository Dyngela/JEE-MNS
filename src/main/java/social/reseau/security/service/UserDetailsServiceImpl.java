package social.reseau.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import social.reseau.security.model.UserPrincipal;
import social.reseau.users.model.User;

import java.time.Duration;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /*
    @Autowired
    private UserRepository userRepository;
    */
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient userAPI = WebClient.create("http://localhost:8084/api/users");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User NOT Found"));
        User user = userAPI
                        .get()
                        .uri("/username/" + username)
                        .retrieve()
                        .bodyToMono(User.class)
                        .block(REQUEST_TIMEOUT);
        return new UserPrincipal(user);
    }
}
