package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.model.dto.TypeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * A repository to manage {@link RoomType} instances.
 *
 * @author Jegors Čemisovs
 */
@Repository
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    @RestResource(path = "byName")
    Optional<RoomType> findFirstByName(String name);

    List<TypeAvailability> getAvailability(
            @Param("arrivingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivingDate,
            @Param("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate);

}
