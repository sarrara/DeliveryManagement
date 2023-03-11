package pidev.elbey.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pidev.elbey.Entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
