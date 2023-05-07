package lv.id.jc.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Booking schedule",
        description = "Schedule of booking hotel rooms for the selected period of time"
)
@RestController
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService service;

    public ScheduleController(@Qualifier("scheduleServiceSQL") ScheduleService service) {
        this.service = service;
    }

    @Operation(
            summary = "Get a room schedule",
            description = "schedule of a given room for a specific period",
            security = @SecurityRequirement(
                    name = "BasicAuthentication"
            ),
            parameters = @Parameter(
                    name = "scheduleRequest",
                    required = true
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @GetMapping
    public List<BookingInfo> getRoomSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        return service.getSchedule(scheduleRequest);
    }

}
