package social.reseau.security.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO implements Serializable {

    @NotEmpty(message = "First Name can not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name can not be empty")
    private String lastName;

    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Username can not be empty")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    private String password;

}
