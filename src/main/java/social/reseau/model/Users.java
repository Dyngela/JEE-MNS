package social.reseau.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Users implements Serializable {

    private Long idUser;

    private String name;

    private String surname;

    private List<Comment> comments;

    private List<Post> posts;



}
