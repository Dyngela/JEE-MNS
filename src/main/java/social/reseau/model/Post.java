package social.reseau.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class Post {
    private Long idMessage;
    private Long idComment;
    private Long idUser;

    private String content;
    private Timestamp date;
}
