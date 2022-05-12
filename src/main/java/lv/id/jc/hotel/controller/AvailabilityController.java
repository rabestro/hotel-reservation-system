package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.AvailabilityRequest;
import lv.id.jc.hotel.model.dto.AvailabilityResponse;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    @Autowired
    AvailabilityService service;

    @GetMapping
    public List<AvailabilityResponse> getAvailability(@RequestBody @Valid AvailabilityRequest request) {
        return service.getAvailability(request);
    }
}
