package social.reseau.users.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class UserForm implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthDateDay;
    private String birthDateMonth;
    private String birthDateYear;
    private String gender;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;

}
