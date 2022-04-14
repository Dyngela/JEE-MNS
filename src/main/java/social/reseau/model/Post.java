package social.reseau.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class Post implements Serializable {

    private Long idMessage;

    private String content;

    private Timestamp date;

    private List<Comment> comments;

    private Users user;

    private Long idUser;

}
