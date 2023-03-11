package pidev.elbey.Entities;

import com.twilio.rest.lookups.v1.PhoneNumber;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
private String phoneNumber;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // load the user and load their roles in the db
    private Collection<Roles> roles = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Tender> tenderSet;
    @ManyToOne ()
    private Forum forum;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Product> Products;

    @OneToOne()
    private Orders orders;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Delivery> deliveries;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="user")
    private Set<Offer> offers;

    public int getDeliveryCountForMonth(int year, int month) {
        int count = 0;
        for (Delivery delivery : deliveries) {
            LocalDateTime deliveryDate = delivery.getDateDelivery().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (deliveryDate.getYear() == year && deliveryDate.getMonthValue() == month) {
                count++;
            }
        }
        return count;
    }

}
