package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.RoomResponse;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    ReservationRepository repository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public boolean isRoomTypeAvailable(BookingRequest request) {
        return repository.isRoomTypeAvailable(request.typeId(), request.checkIn(), request.checkOut());
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate arrivingDate, LocalDate departureDate) {
        return repository.isRoomAvailable(roomId, arrivingDate, departureDate);
    }

    @Override
    public List<RoomResponse> findAvailableRooms(BookingRequest request) {
        return roomRepository
                .findAvailableRooms(request.typeId(), request.checkIn(), request.checkOut())
                .map(RoomResponse::new)
                .toList();
    }
}
