package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

import static org.hibernate.annotations.QueryHints.CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

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
    @QueryHints({
            @QueryHint(name = CACHEABLE, value = "true"),
            @QueryHint(name = HINT_COMMENT, value = "user by email address")})
    Optional<User> findFirstByEmailIgnoreCase(String email);

    /**
     * Get all users with the specific role
     *
     * @param role of the user
     * @return the list of user entities with the given role
     */
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "users with the specific role"))
    List<User> findByRole(User.Role role);

}
