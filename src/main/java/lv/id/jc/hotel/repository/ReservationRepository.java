package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
   @Query("""
      SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
      FROM Reservation r
      WHERE r.room.id = ?1 AND r.checkIn <= ?2 AND r.checkOut > ?2
   """)
   boolean existsReservationByRoomAndDate(Long roomId, LocalDate date);

   @Query("""
      SELECT COUNT(r) = 0
      FROM Reservation r
      WHERE r.room.id = :id AND r.checkIn <= :date AND r.checkOut > :date
   """)
   boolean isRoomAvailableByDate(@Param("id") Long roomId, @Param("date") LocalDate date);
}

