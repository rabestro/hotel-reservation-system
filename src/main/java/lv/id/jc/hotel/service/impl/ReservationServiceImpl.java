package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.Reservation;
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

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    @Transactional
    public ReservationDetails book(UserDetails userDetails, BookingRequest request) {
        var guest = userRepository.findFirstByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow();

        var room = roomRepository
                .findAvailableRooms(request.typeId(), request.checkIn(), request.checkOut())
                .stream().findFirst().orElseThrow();

        var reservation = new Reservation(room, guest, request.checkIn(), request.checkOut());
        return new ReservationDetails(reservationRepository.save(reservation));
    }

}
