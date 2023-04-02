package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.projection.BookingInfo;
import lv.id.jc.hotel.service.ScheduleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Schedule of booking hotel rooms for the selected period of time.
 * This request is available only to hotel employees.
 */
@RestController
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService service;

    public ScheduleController(@Qualifier("scheduleServiceSQL") ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookingInfo> getRoomSchedule(@RequestBody @Valid ScheduleRequest request) {
        return service.getSchedule(request);
    }

}
