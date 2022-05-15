package lv.id.jc.hotel.model.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record AvailabilityRequest(
        @NotNull @FutureOrPresent LocalDate checkIn,
        @NotNull @Future LocalDate checkOut) {

    @AssertTrue(message = "checkIn must be before checkOut")
    private boolean isValidPeriod() {
        return checkIn != null && checkOut != null && checkIn.isBefore(checkOut);
    }
}