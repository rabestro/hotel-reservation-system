package lv.id.jc.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.dto.DailyStatistics;
import lv.id.jc.hotel.model.dto.StatisticsRequest;
import lv.id.jc.hotel.service.StatisticsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Tag(
        name = "Availability statistics",
        description = "Hotel availability statistics (how many rooms are free/busy) for a specified period"
)
@RestController
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService service;

    @Operation(
            summary = "Get statistic",
            description = "availability statistics for a specified period",
            security = @SecurityRequirement(
                    name = "BasicAuthentication"
            ),
            parameters = @Parameter(
                    name = "statisticsRequest",
                    required = true
            )
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @GetMapping
    public List<DailyStatistics> getStatistics(@RequestBody @Valid StatisticsRequest statisticsRequest) {
        return service.getStatistics(statisticsRequest);
    }
}
