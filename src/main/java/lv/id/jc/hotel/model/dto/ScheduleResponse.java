package lv.id.jc.hotel.model.dto;

import java.time.LocalDate;

public record ScheduleResponse(LocalDate date, Long reservationId, String guest) {

    public boolean isBooked() {
        return reservationId() != null;
    }
}
