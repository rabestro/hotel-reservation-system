package lv.id.jc.hotel.service;

import lv.id.jc.hotel.dto.BookingRequest;
import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.Room;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

public interface ReservationService {
    Reservation book(UserDetails userDetails, BookingRequest bookingRequest);

    boolean isBooked(Long roomId, LocalDate date);

    boolean isRoomAvailable(Long roomId, LocalDate date);
}
