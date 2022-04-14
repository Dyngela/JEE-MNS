package social.reseau.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class Comment implements Serializable {

    private Long idComment;

    private String content;

    private Timestamp date;

    private Post post;

    private Users user;

    private Long idUser;

    private Long idMessage;

}
