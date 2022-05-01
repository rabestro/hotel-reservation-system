package lv.id.jc.hotel.model.dto;

import java.time.LocalDate;
import java.util.List;

public record DailyStatistics(LocalDate date, long free, long booked, List<AvailableRooms> availableRooms) {
}
