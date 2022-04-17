package social.reseau.security.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
}
