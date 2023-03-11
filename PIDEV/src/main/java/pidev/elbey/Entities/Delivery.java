package pidev.elbey.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor

public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;
    private String destination;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "Delivery_creation_date", nullable = false)
    private Date dateDelivery;

    @JsonIgnore
    @OneToMany(cascade =CascadeType.ALL)
    private Set<Orders> Orders;

    @OneToOne(mappedBy="delivery")
    private BillToSeen billtoseen;
    @ManyToOne
    private User user ;
    public Month getDeliveryMonth() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(dateDelivery.toInstant(), ZoneId.systemDefault());
        return localDateTime.getMonth();
    }
}
