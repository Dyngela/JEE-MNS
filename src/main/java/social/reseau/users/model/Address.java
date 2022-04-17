package social.reseau.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Address implements Serializable {

	private Long id;
	private Integer number;
	private String street;
	private City city;

}
