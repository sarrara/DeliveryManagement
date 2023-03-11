package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.BillToSeen;


@Repository
public interface BillToSeenRepo   extends JpaRepository<BillToSeen, Long> {
}
