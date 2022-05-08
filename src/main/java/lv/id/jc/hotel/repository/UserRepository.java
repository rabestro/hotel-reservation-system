package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A repository to manage {@link User} instances.
 *
 * @author Jegors Čemisovs
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmailIgnoreCase(String email);

    List<User> findByRole(User.Role role);
}
