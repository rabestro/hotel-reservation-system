package lv.id.jc.hotel.model.dto;

import java.time.LocalDate;

public record StatisticsRequest(LocalDate startDate, LocalDate endDate) {
}
