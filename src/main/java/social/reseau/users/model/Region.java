package social.reseau.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Region  implements Serializable {

    private Long id;
    private String name;
    private String country;
    private String department;
    private Integer postCode;

}
