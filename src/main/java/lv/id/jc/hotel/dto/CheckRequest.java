package lv.id.jc.hotel.dto;

import java.time.LocalDate;

public record CheckRequest(Long room, LocalDate date) {
}
