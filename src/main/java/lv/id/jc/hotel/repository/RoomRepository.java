package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.model.projection.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.util.Streamable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * A repository to manage {@link Room}s.
 *
 * @author Jegors Čemisovs
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @RestResource(path = "byNumber")
    Optional<Room> findByNumber(String number);

    @Query("SELECT COUNT(r) = 0 FROM Reservation r WHERE r.room.id = :id AND r.checkIn <= :date AND r.checkOut > :date")
    boolean isRoomAvailableByDate(
            @Param("id") Long roomId,
            @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    Streamable<Room> findAvailableRooms(
            @Param("typeId") Long typeId,
            @Param("arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivalDate,
            @Param("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate);

    /**
     * This method calculates the timetable for a hotel room.
     *
     * @param roomId    hotel room ID
     * @param startDate schedule start date
     * @param endDate   last date of the schedule
     * @return timetable for the hotel room
     */
    @Query(nativeQuery = true)
    @RestResource(exported = false)
    List<BookingInfo> getSchedule(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);


}
