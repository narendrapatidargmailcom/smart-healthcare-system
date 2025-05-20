package healthcare.repository;

import healthcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Integer, User> {
}
