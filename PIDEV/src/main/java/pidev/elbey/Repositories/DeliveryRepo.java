package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Delivery;

import pidev.elbey.Entities.DeliveryStatus;
import pidev.elbey.Entities.User;

import java.time.Month;
import java.util.Date;
import java.util.List;

@Repository

public interface DeliveryRepo  extends JpaRepository<Delivery, Long> {
    @Query("SELECT d FROM Delivery d WHERE d.destination LIKE %?1%")
          //  + " OR d.dateDelivery LIKE %?1%")

    public List<Delivery> search(String keyword);
    @Query(value = "SELECT u.*, COUNT(d.id_delivery) as delivery_count " +
            "FROM Delivery d " +
            "JOIN User u ON d.user_id = u.id_user " +
            "WHERE MONTH(d.delivery_creation_date) = :month " +
            "GROUP BY u.id_user " +
            "ORDER BY delivery_count DESC " +
            "LIMIT 1", nativeQuery = true)
    User findUserWithMostDeliveriesForMonth(@Param("month") int month);


    List<Delivery> findByDeliveryStatus(DeliveryStatus delivered);
}
