package lv.id.jc.hotel.model.dto;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;

public record ScheduleRequest(Long roomId, LocalDate startDate, LocalDate endDate) {
    @AssertTrue(message = "startDate must not be after endDate")
    private boolean isValidPeriod() {
        return !startDate.isAfter(endDate);
    }
}
