package social.reseau.users.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import social.reseau.security.model.UserPrincipal;
import social.reseau.users.model.User;

import java.io.Reader;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient userAPI = WebClient.create("http://localhost:8084/api/users");

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

    public Object getAll(Integer pageNumber, Integer pageSize) {
        return userAPI
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/page")
                        .queryParam("pageNumber", pageNumber)
                        .queryParam("pageSize", pageSize)
                        .build())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public User getById(Long id) {
        return userAPI
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(User.class)
                .block(REQUEST_TIMEOUT);
    }

    public User create(User user) {
        return userAPI
                .post()
                .uri("")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block(REQUEST_TIMEOUT);
    }

    public User update(User user) {
        return userAPI
                .put()
                .uri("/" + user.getId())
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block(REQUEST_TIMEOUT);
    }

    public void delete(Long id) {
        userAPI
                .delete()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
