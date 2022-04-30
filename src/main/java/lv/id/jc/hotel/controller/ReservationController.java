package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.BookingRequest;
import lv.id.jc.hotel.dto.CheckRequest;
import lv.id.jc.hotel.model.Reservation;
import lv.id.jc.hotel.service.ReservationService;
import lv.id.jc.hotel.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/reservation")
public record ReservationController(UserService userService, ReservationService service) {

    @PostMapping
    public Reservation book(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid BookingRequest bookingRequest) {

        return new Reservation();
    }

    @GetMapping("check1")
    public boolean isReserved(@RequestParam Long room, @RequestParam LocalDate date) {
        return service().isRoomBooked(room, date);
    }

    @GetMapping("check2")
    public boolean check(@RequestBody @Valid CheckRequest request) {
        return service().isRoomBooked(request.room(), request.date());

    }

    @GetMapping("check3")
    public boolean isAvailable(@RequestBody @Valid CheckRequest request) {
        return service().isRoomAvailable(request.room(), request.date());

    }


}
