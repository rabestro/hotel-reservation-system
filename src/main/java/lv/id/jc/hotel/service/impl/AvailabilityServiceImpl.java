package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public record AvailabilityServiceImpl(ReservationRepository repository) implements AvailabilityService {

    @Override
    public boolean isRoomTypeAvailable(Long typeId, LocalDate arrivingDate, LocalDate departureDate) {
        return repository().isRoomTypeAvailable(typeId, arrivingDate, departureDate);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate arrivingDate, LocalDate departureDate) {
        return repository().isRoomAvailable(roomId, arrivingDate, departureDate);
    }

}
