package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A repository to manage {@link RoomType} instances.
 *
 */
@Repository
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    @RestResource(path = "byName")
    Optional<RoomType> findFirstByName(String name);

}
