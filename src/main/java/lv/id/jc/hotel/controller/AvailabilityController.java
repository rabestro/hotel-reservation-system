package lv.id.jc.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.dto.AvailabilityRequest;
import lv.id.jc.hotel.model.dto.AvailabilityResponse;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name = "Rooms availability",
        description = "Availability of hotel rooms by room type for the selected period"
)
@RestController
@RequestMapping("/availability")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService service;

    @Operation(summary = "Find availability of hotel rooms for type and period")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<AvailabilityResponse> getAvailability(@RequestBody @Valid AvailabilityRequest request) {
        return service.getAvailability(request);
    }
}
