package pidev.elbey.Entities;

import java.io.Serializable;
import java.util.Set;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor

public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromotion;
    private float percentage;
    @ManyToMany(mappedBy= "discounts",
            cascade = CascadeType.ALL)
    private Set<Orders> orders;
}
