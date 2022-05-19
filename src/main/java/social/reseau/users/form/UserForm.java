package social.reseau.users.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class UserForm implements Serializable {

    private Long id;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    private String birthDateDay;

    private String birthDateMonth;

    private String birthDateYear;

    @NotNull
    private String gender;

    private String phoneNumber;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "username is mandatory")
    private String username;

    private String password;

}
