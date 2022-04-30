package lv.id.jc.hotel.model.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

public record BookingRequest(Long typeId, @FutureOrPresent LocalDate checkIn, @Future LocalDate checkOut) {

    @AssertTrue(message = "checkIn must be before checkOut")
    private boolean isValidPeriod() {
        return checkIn.isBefore(checkOut);
    }

}
