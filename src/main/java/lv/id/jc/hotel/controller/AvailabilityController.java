package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.RoomResponse;
import lv.id.jc.hotel.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/check")
public class AvailabilityController {
    @Autowired
    AvailabilityService service;

    @GetMapping("/room/{id}")
    public boolean isRoomAvailable(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @FutureOrPresent LocalDate arrivingDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Future LocalDate departureDate) {
        return service.isRoomAvailable(id, arrivingDate, departureDate);
    }

    @GetMapping("/type")
    public boolean isTypeAvailable(@RequestBody @Valid BookingRequest bookingRequest) {
        return service.isRoomTypeAvailable(bookingRequest);
    }

    @GetMapping
    public List<RoomResponse> findAvailableRooms(@RequestBody @Valid BookingRequest bookingRequest) {
        return service.findAvailableRooms(bookingRequest);
    }

}
