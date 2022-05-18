package social.reseau.security.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO implements Serializable {

    @NotEmpty(message = "Username can not be empty")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    private String password;
}
