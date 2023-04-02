package lv.id.jc.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.projection.BookingInfo;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service implementation uses the capabilities
 * of the SQL query language to generate a room schedule.
 * <p>
 * Thus, the request is redirected to the repository.
 */
@Service("scheduleServiceSQL")
@RequiredArgsConstructor
public class ScheduleServiceSQL implements ScheduleService {
    private final RoomRepository roomRepository;

    @Override
    public List<BookingInfo> getSchedule(ScheduleRequest request) {
        return roomRepository.getSchedule(request.roomId(), request.startDate(), request.endDate());
    }
}
