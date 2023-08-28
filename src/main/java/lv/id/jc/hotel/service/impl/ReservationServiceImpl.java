package lv.id.jc.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.repository.UserRepository;
import lv.id.jc.hotel.service.ReservationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private static final Supplier<RuntimeException> NO_FREE_ROOM = () -> new NoSuchElementException(
            "There are no available rooms of the specified type for the selected period");
    private static final Supplier<RuntimeException> CUSTOMER_NOT_FOUND = () -> new NoSuchElementException(
            "Only registered customers can book a room");

    private static final Predicate<@Valid User> isCustomer = user -> User.Role.CUSTOMER == user.getRole();

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public ReservationDetails book(UserDetails userDetails, @Valid BookingRequest request) {
        var guest = userRepository.findFirstByEmailIgnoreCase(userDetails.getUsername())
                .filter(isCustomer)
                .orElseThrow(CUSTOMER_NOT_FOUND);

        var room = roomRepository
                .findAvailableRooms(request.typeId(), request.checkIn(), request.checkOut())
                .stream()
                .findFirst()
                .orElseThrow(NO_FREE_ROOM);

        var reservation = new Reservation(room, guest, request.checkIn(), request.checkOut());
        return new ReservationDetails(reservationRepository.save(reservation));
    }

}