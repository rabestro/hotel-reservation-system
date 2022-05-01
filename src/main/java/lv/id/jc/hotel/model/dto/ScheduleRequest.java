package lv.id.jc.hotel.model.dto;

import java.time.LocalDate;

public record ScheduleRequest(Long roomId, LocalDate startDate, LocalDate endDate) {
}
