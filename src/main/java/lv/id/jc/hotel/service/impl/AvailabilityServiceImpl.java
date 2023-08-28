package lv.id.jc.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.dto.AvailabilityRequest;
import lv.id.jc.hotel.model.dto.AvailabilityResponse;
import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {
    private final RoomTypeRepository roomTypeRepository;

    @Override
    public List<AvailabilityResponse> getAvailability(@Valid AvailabilityRequest request) {
        return roomTypeRepository.getAvailability(request.checkIn(), request.checkOut());
    }
}