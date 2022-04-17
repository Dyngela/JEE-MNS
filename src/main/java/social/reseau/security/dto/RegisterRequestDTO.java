package social.reseau.security.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String username;
    private String password;

}
