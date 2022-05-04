package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.service.ReservationService;
import lv.id.jc.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserService userService;
    @Autowired
    ReservationRepository repository;

    @Override
    public ReservationDetails book(UserDetails userDetails, BookingRequest request) {
        var room = repository
                .findAvailableRooms(request.typeId(), request.checkIn(), request.checkOut())
                .stream().findFirst().orElseThrow();

        var guest = userService
                .findByEmail(userDetails.getUsername())
                .orElseThrow();

        var reservation = new Reservation()
                .setGuest(guest)
                .setRoom(room)
                .setCheckIn(request.checkIn())
                .setCheckOut(request.checkOut());

        return new ReservationDetails(repository.save(reservation));
    }

    @Override
    public boolean isRoomBooked(Long roomId, LocalDate date) {
        return repository.existsReservationByRoomAndDate(roomId, date);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate date) {

        return repository.isRoomAvailableByDate(roomId, date);
    }

    @Override
    public List<ReservationDetails> findAllReservations(User guest) {
        throw new UnsupportedOperationException("This operation has not yet been implemented");
    }

    @Override
    public List<ReservationDetails> findActualReservations(User guest) {
        throw new UnsupportedOperationException("This operation has not yet been implemented");
    }

    @Override
    public List<ReservationDetails> findPastReservations(User guest) {
        throw new UnsupportedOperationException("This operation has not yet been implemented");
    }

    @Override
    public void cancel(Long id) {
        throw new UnsupportedOperationException("This operation has not yet been implemented");
    }

}
