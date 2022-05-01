package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.CheckRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import lv.id.jc.hotel.service.ReservationService;
import lv.id.jc.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/book")
public class ReservationController {
    @Autowired
    UserService userService;
    @Autowired
    ReservationService service;

    @PostMapping
    public ReservationDetails book(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid BookingRequest bookingRequest) {

        return service.book(userDetails, bookingRequest);
    }

    @GetMapping("check1")
    public boolean isReserved(@RequestParam Long room, @RequestParam LocalDate date) {
        return service.isRoomBooked(room, date);
    }

    @GetMapping("check2")
    public boolean check(@RequestBody @Valid CheckRequest request) {
        return service.isRoomBooked(request.room(), request.date());

    }

    @GetMapping("check3")
    public boolean isAvailable(@RequestBody @Valid CheckRequest request) {
        return service.isRoomAvailable(request.room(), request.date());

    }


}
