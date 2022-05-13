package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.projection.Schedule;

import java.util.List;

public interface ScheduleService {
    /**
     * Get a schedule for a room for a period of time.
     *
     * @param request a schedule request
     * @return a schedule for the given room and the given period
     */
    List<Schedule> getSchedule(ScheduleRequest request);
}
