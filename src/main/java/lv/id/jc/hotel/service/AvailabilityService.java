package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.dto.AvailabilityRequest;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.RoomResponse;
import lv.id.jc.hotel.model.dto.AvailabilityResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * A service for checking the availability of hotel rooms or room types for a given day or period.
 */
public interface AvailabilityService {

    /**
     * Checks the availability of a hotel rooms for a given period of time.
     *
     * @param request contains arrival and departure dates 
     * @return list of available room types with number of free rooms for the given period
     */
    List<AvailabilityResponse> getAvailability(AvailabilityRequest request);
}
