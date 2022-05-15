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

    /**
     * Find user by email address ignore case
     *
     * @param email a email address
     * @return the user entity with the given email or {@literal Optional#empty()} if none found.
     */
    Optional<User> findFirstByEmailIgnoreCase(String email);

    List<User> findByRole(User.Role role);

}
