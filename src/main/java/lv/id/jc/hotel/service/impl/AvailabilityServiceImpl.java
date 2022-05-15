package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.dto.AvailabilityRequest;
import lv.id.jc.hotel.model.dto.AvailabilityResponse;
import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Override
    public List<AvailabilityResponse> getAvailability(AvailabilityRequest request) {
        return roomTypeRepository.getAvailability(request.checkIn(), request.checkOut());
    }
}
