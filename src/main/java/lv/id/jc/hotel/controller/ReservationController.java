package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import lv.id.jc.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ReservationDetails book(@AuthenticationPrincipal UserDetails userDetails,
                                   @RequestBody @Valid BookingRequest bookingRequest) {
        return reservationService.book(userDetails, bookingRequest);
    }

}
