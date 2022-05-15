package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.ReservationDetails;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Hotel reservation service for registered customers
 */
public interface ReservationService {

    /**
     * Room reservation
     *
     * @param userDetails    details of a registered customer
     * @param bookingRequest details of booking request
     * @return confirmation of hotel room reservation
     */
    ReservationDetails book(UserDetails userDetails, BookingRequest bookingRequest);

}
