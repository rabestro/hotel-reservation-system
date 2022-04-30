package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.model.dto.BookingRequest;
import lv.id.jc.hotel.model.dto.RoomResponse;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * A service for checking the availability of hotel rooms or room types for a given day or period.
 */
public interface AvailabilityService {

    /**
     * Checks the availability of a hotel room for a given period of time.
     *
     * @param roomId hotel room id
     * @param arrivingDate a guest arriving date
     * @param departureDate a guest departure date
     * @return true if the room is available for the period
     */
    boolean isRoomAvailable(Long roomId, LocalDate arrivingDate, LocalDate departureDate);

    /**
     * Checks the availability of a certain type of hotel room for a given period of time.
     *
     * @param bookingRequest request to book a specific type of room for a specific period
     * @return true if at least one room of given type is available for the period
     */
    boolean isRoomTypeAvailable(BookingRequest bookingRequest);

    /**
     * Find all available rooms of a certain type for a given period of time.
     *
     * @param bookingRequest request to book a specific type of room for a specific period
     * @return list of available rooms of the given type for the given period
     */
    List<RoomResponse> findAvailableRooms(BookingRequest bookingRequest);
}
