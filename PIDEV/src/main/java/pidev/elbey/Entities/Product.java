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

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private float priceProduct;
    private int quantityProduct;
    private String descriptionProduct;
    @ManyToOne
    private User user ;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="product")
    private Set<Tender> tenders;

    @ManyToOne(cascade = CascadeType.ALL)
    private Basket basket;
}
