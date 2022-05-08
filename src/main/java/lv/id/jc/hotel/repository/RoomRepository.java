package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * A repository to manage {@link Room}s.
 *
 * @author Jegors Čemisovs
 */
@Repository
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public interface RoomRepository extends JpaRepository<Room, Long> {

    @RestResource(path = "byNumber")
    Optional<Room> findFirstByNumber(String number);

    @Query("SELECT COUNT(r) = 0 FROM Reservation r WHERE r.room.id = :id AND r.checkIn <= :date AND r.checkOut > :date")
    boolean isRoomAvailableByDate(@Param("id") Long roomId, @Param("date") LocalDate date);

}
