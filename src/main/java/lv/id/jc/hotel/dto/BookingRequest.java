package lv.id.jc.hotel.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

public record BookingRequest(Long typeId, @FutureOrPresent LocalDate checkIn, @Future LocalDate checkOut) {
}
