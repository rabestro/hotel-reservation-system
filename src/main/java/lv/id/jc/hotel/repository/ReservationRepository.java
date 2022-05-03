package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("FROM Reservation r WHERE r.room.id = :roomId AND r.checkIn <= :date AND r.checkOut > :date")
    Optional<Reservation> findReservation(@Param("roomId") Long roomId, @Param("date") LocalDate date);

    @Query("SELECT count(DISTINCT r.room) FROM Reservation r WHERE r.checkIn <= :date AND r.checkOut > :date")
    long countBusyRooms(@Param("date") LocalDate date);

    @Query("""
               SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
               FROM Reservation r
               WHERE r.room.id = :id AND r.checkIn <= :date AND r.checkOut > :date
            """)
    boolean existsReservationByRoomAndDate(@Param("id") Long roomId, @Param("date") LocalDate date);

    @Query("""
               SELECT COUNT(r) = 0
               FROM Reservation r
               WHERE r.room.id = :id AND r.checkIn <= :date AND r.checkOut > :date
            """)
    boolean isRoomAvailableByDate(@Param("id") Long roomId, @Param("date") LocalDate date);

    @Query("""
               SELECT COUNT(r) = 0
               FROM Reservation r
               WHERE r.room.id = :roomId AND
               (
                  r.checkIn <= :arrivingDate AND r.checkOut > :arrivingDate
                  OR
                  r.checkIn < :departureDate AND r.checkOut >= :departureDate
                  OR
                  r.checkIn <= :arrivingDate AND r.checkOut >= :departureDate
                  OR
                  r.checkIn > :arrivingDate AND r.checkOut < :departureDate
               )
            """)
    boolean isRoomAvailable(
            @Param("roomId") Long roomId,
            @Param("arrivingDate") LocalDate arrivingDate,
            @Param("departureDate") LocalDate departureDate);

    @Query("""
               SELECT COUNT(rm) > 0
               FROM Room rm
               WHERE rm.type.id = :typeId AND rm.id NOT IN (
                   SELECT distinct r.room.id
                   FROM Reservation r
                   WHERE r.room.type.id = :typeId AND
                   (
                      r.checkIn <= :arrivingDate AND r.checkOut > :arrivingDate
                      OR
                      r.checkIn < :departureDate AND r.checkOut >= :departureDate
                      OR
                      r.checkIn <= :arrivingDate AND r.checkOut >= :departureDate
                      OR
                      r.checkIn > :arrivingDate AND r.checkOut < :departureDate
                   )
               )
            """)
    boolean isRoomTypeAvailable(
            @Param("typeId") Long typeId,
            @Param("arrivingDate") LocalDate arrivingDate,
            @Param("departureDate") LocalDate departureDate);

    @Query("""
               FROM Room rm
               WHERE rm.type.id = :typeId AND rm.id NOT IN (
                   SELECT distinct r.room.id
                   FROM Reservation r
                   WHERE r.room.type.id = :typeId AND
                   (
                      r.checkIn <= :arrivingDate AND r.checkOut > :arrivingDate
                      OR
                      r.checkIn < :departureDate AND r.checkOut >= :departureDate
                      OR
                      r.checkIn <= :arrivingDate AND r.checkOut >= :departureDate
                      OR
                      r.checkIn > :arrivingDate AND r.checkOut < :departureDate
                   )
               )
            """)
    Streamable<Room> findAvailableRooms(
            @Param("typeId") Long typeId,
            @Param("arrivingDate") LocalDate arrivingDate,
            @Param("departureDate") LocalDate departureDate);
}

