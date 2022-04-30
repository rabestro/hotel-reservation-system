package lv.id.jc.hotel.service;

import java.time.LocalDate;

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
     * @param typeId hotel room type id
     * @param arrivingDate a guest arriving date
     * @param departureDate a guest departure date
     * @return true if at least one room of given type is available for the period
     */
    boolean isRoomTypeAvailable(Long typeId, LocalDate arrivingDate, LocalDate departureDate);
}
