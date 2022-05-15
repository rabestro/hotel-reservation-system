package lv.id.jc.hotel.model.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record ScheduleRequest(@NotNull Long roomId, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {
    @AssertTrue(message = "startDate must not be after endDate")
    private boolean isValidPeriod() {
        return !startDate.isAfter(endDate);
    }
}
