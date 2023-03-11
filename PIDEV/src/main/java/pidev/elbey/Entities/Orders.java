package pidev.elbey.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor

public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    private String descriptionOrder;
    private double totalOrder;
    private String codePromo;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOrdre", nullable = false)
    private Date dateOrdre;

    @OneToOne(mappedBy= "orders")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy= "orders")
    private Set<Reclamation> reclamations;

    @OneToOne
    private Payment payment;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Discount> discounts;
}
