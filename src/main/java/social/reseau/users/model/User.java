package social.reseau.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class User implements Serializable {

        private Long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String phoneNumber;
        private Gender gender;
        private Address address;
        private String username;
        private String password;
        private String email;
        private Boolean isEnabled = true;
        private Set<Role> roles = new HashSet<>();

}
