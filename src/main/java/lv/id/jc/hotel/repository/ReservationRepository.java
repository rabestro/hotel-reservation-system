package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.time.LocalDate;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * A repository to manage {@link Reservation}s.
 *
 * @author Jegors Čemisovs
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Calculate count of booked hotel rooms for a specific date
     *
     * @param date a date for check
     * @return count of booked rooms
     */
    @Query("SELECT COUNT(DISTINCT r.room) FROM Reservation r WHERE r.checkIn <= :date AND r.checkOut > :date")
    @QueryHints(@QueryHint(name = HINT_COMMENT, value = "busy rooms for a specific date"))
    long countBusyRooms(@Param("date") LocalDate date);

}

