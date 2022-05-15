package lv.id.jc.hotel.model.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

/**
 * Booking information for a hotel room per day
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface BookingInfo {
    LocalDate getDate();

    Long getBookId();

    Long getGuestId();

    String getName();

    String getEmail();
}
