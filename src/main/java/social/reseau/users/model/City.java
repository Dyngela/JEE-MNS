package social.reseau.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class City implements Serializable {

	private Long id;
	private String name;
	private Integer postCode;
	private Region region;

}
