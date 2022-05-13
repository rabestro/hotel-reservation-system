package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.repository.UserRepository;
import lv.id.jc.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    @Transactional
    public ReservationDetails book(UserDetails userDetails, BookingRequest request) {
        var guest = userRepository.findFirstByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow();

        var room = roomRepository
                .findAvailableRooms(request.typeId(), request.checkIn(), request.checkOut())
                .stream().findFirst().orElseThrow();

        var reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setCheckIn(request.checkIn());
        reservation.setCheckOut(request.checkOut());
        return new ReservationDetails(reservationRepository.save(reservation));
    }

    @Override
    public boolean isRoomBooked(Long roomId, LocalDate date) {
        return reservationRepository.existsReservationByRoomAndDate(roomId, date);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate date) {

        return reservationRepository.isRoomAvailableByDate(roomId, date);
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

}
