package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.model.projection.BookingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.util.Streamable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.annotations.QueryHints.CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * A repository to manage {@link Room}s.
 *
 * @author Jegors Čemisovs
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Find a room entity by room number
     *
     * @param number of hotel room
     * @return the room entity with the given number or {@literal Optional#empty()} if none found.
     */
    @RestResource(path = "byNumber")
    @QueryHints({
            @QueryHint(name = CACHEABLE, value = "true"),
            @QueryHint(name = HINT_COMMENT, value = "find a room by number")})
    Optional<Room> findByNumber(String number);

    /**
     * Check is room available for specific day
     *
     * @param roomId the id of hotel room
     * @param date   for which we want to check room availability
     * @return true if the room is available on the specified date
     */
    @Query("SELECT COUNT(r) = 0 FROM Reservation r WHERE r.room.id = :id AND r.checkIn <= :date AND r.checkOut > :date")
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "is room available for specific day"))
    boolean isRoomAvailableByDate(
            @Param("id") Long roomId,
            @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    /**
     * Find available rooms of certain room type for a period
     *
     * @param typeId        hotel room type
     * @param arrivalDate   a guest arrival date (check-in)
     * @param departureDate a departure date (check-out)
     * @return stream of available rooms
     */
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "available rooms of specific type"))
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
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "timetable for a hotel room"))
    List<BookingInfo> getSchedule(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
