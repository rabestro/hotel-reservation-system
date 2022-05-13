package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.projection.Schedule;
import lv.id.jc.hotel.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    @Qualifier("scheduleServiceSQL")
    ScheduleService service;

    @GetMapping
    public List<Schedule> getRoomSchedule(@RequestBody @Valid ScheduleRequest request) {
        return service.getSchedule(request);
    }

}
