package pidev.elbey.Entities;

import java.io.Serializable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long idPost;
    private String description;
    private String image;
    @Temporal(TemporalType.DATE)
    @Column(name = "datePublication", nullable = false)
    private Date datePublication;

    @ManyToOne(cascade = CascadeType.ALL)
    private Forum forum;


}
