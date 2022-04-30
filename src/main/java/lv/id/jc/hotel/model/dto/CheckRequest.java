package lv.id.jc.hotel.model.dto;

import java.time.LocalDate;

public record CheckRequest(Long room, LocalDate date) {
}
