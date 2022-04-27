package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
