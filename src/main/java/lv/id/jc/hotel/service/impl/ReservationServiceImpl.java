package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.dto.BookingRequest;
import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.service.ReservationService;
import lv.id.jc.hotel.service.RoomService;
import lv.id.jc.hotel.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public record ReservationServiceImpl(UserService userService, RoomService roomService,
        ReservationRepository repository) implements ReservationService {

    @Override
    public Reservation book(UserDetails userDetails, BookingRequest bookingRequest) {
        return null;
    }

    @Override
    public boolean isRoomBooked(Long roomId, LocalDate date) {
        return repository().existsReservationByRoomAndDate(roomId, date);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate date) {

        return repository().isRoomAvailableByDate(roomId, date);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate arrivingDate, LocalDate departureDate) {
        return repository().isRoomAvailable(roomId, arrivingDate, departureDate);
    }
}
