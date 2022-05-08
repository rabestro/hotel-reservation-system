package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

/**
 * A repository to manage {@link RoomType} instances.
 *
 * @author Jegors Čemisovs
 */
@Repository
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
