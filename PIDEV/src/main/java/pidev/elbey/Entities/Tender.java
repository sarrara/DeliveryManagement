package pidev.elbey.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor
public class Tender implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTender;
    private String nameCall;
    private String detail;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateCall", nullable = false)
    private Date dateCall;

    @ManyToMany (mappedBy= "tenderSet",
       cascade = CascadeType.ALL)
    private Set<User> Users;
    @ManyToOne
    private Product product ;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Offer> offers;
}
