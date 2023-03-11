package pidev.elbey.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor
public class BillToSeen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBillToSeen;
    private Date billDate;
    private final float tva =19;
    private float totalHT;
    private float totalTTC;

    @JsonIgnore
   @OneToOne
    private Delivery delivery;
}
