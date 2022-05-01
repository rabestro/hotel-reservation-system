package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationDetails book(UserDetails userDetails, BookingRequest bookingRequest);

    boolean isRoomBooked(Long roomId, LocalDate date);

    boolean isRoomAvailable(Long roomId, LocalDate date);

    /**
     * Find all reservations for the given user
     *
     * @param guest a guest for whom the reservation is made
     * @return all reservation for the given guest
     */
    List<ReservationDetails> findAllReservations(User guest);

    /**
     * Find all actual reservations for the given user
     *
     * @param guest a guest for whom the reservation is made
     * @return all actual reservations for the given guest
     */
    List<ReservationDetails> findActualReservations(User guest);

    /**
     * Find all past reservations for the given user
     *
     * @param guest a guest for whom the reservation is made
     * @return past reservations for the given guest
     */
    List<ReservationDetails> findPastReservations(User guest);

    /**
     * Cancel a reservation
     *
     * @param id of the reservation
     */
    void cancel(Long id);
}
