package lv.id.jc.hotel.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ScheduleResponse(LocalDate date, Long reservationId, String guest) {
    public ScheduleResponse(LocalDate date) {
        this(date, null, null);
    }

    public boolean isBooked() {
        return reservationId() != null;
    }
}
