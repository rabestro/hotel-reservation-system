package lv.id.jc.hotel.model.dto;

import java.time.LocalDate;

public record DailyStatistics(LocalDate date, long free, long busy) {
}
