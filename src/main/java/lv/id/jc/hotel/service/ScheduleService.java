package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    /**
     * Get a schedule for the given room for specified period of time.
     *
     * @param request a schedule request
     * @return list of schedule responses
     */
    List<ScheduleResponse> getSchedule(ScheduleRequest request);
}
