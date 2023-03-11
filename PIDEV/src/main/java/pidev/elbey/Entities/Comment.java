package pidev.elbey.Entities;

import java.io.Serializable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor

public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;
    private String comment;
    private String imageComment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post ;
}
