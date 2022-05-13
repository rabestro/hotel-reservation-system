package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.DailyStatistics;
import lv.id.jc.hotel.model.dto.StatisticsRequest;
import lv.id.jc.hotel.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * See hotel availability statistics (how many rooms are free/busy) for a specified period
 */
@RestController
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService service;

    @GetMapping
    public List<DailyStatistics> getStatistics(@RequestBody @Valid StatisticsRequest request) {
        return service.getStatistics(request);
    }

}
