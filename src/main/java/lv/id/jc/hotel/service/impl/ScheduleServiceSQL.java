package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.projection.Schedule;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service implementation uses the capabilities
 * of the SQL query language to generate a room schedule.
 *
 * Thus, the request is redirected to the repository.
 */
@Service("scheduleServiceSQL")
public class ScheduleServiceSQL implements ScheduleService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Schedule> getSchedule(ScheduleRequest request) {
        return roomRepository.getSchedule(request.roomId(), request.startDate(), request.endDate());
    }
}
