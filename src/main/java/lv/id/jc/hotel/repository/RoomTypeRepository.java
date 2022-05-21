package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.model.dto.AvailabilityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * A repository to manage {@link RoomType} instances.
 *
 * @author Jegors Čemisovs
 */
@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    /**
     * Find room type entity by name
     *
     * @param name of the room type
     * @return the room type entity with the given name or {@literal Optional#empty()} if none found.
     */
    @RestResource(path = "byName")
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "find room type entity by name"))
    Optional<RoomType> findFirstByName(String name);

    /**
     * Checks the availability of a hotel rooms for a given period of time.
     *
     * @param arrivalDate   a guest arrival date
     * @param departureDate a guest departure date
     * @return list of available room types with number of free rooms for the given period
     */
    @RestResource(exported = false)
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "availability of a hotel rooms for a given period of time"))
    List<AvailabilityResponse> getAvailability(@Param("arrivalDate") LocalDate arrivalDate,
                                               @Param("departureDate") LocalDate departureDate);

}
