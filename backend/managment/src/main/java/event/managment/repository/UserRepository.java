package event.managment.repository;

import event.managment.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByUsernameContainingIgnoreCase(String username);

        Optional<User> findByEmailAndPassword(String email, String password);
    

}
