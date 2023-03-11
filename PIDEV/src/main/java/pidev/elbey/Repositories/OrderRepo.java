package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.Orders;
@Repository

public interface OrderRepo  extends JpaRepository<Orders, Long> {
}
