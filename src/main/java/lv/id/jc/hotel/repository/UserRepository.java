package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Role;
import lv.id.jc.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    List<User> findByRole(Role role);
}
