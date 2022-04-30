package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.dto.ReservationConfirmation;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

public interface ReservationService {
    ReservationConfirmation book(UserDetails userDetails, BookingRequest bookingRequest);

    boolean isRoomBooked(Long roomId, LocalDate date);

    boolean isRoomAvailable(Long roomId, LocalDate date);
}
