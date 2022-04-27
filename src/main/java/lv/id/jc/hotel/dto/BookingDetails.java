package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.validator.RoomNumber;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

public record BookingDetails(
        @RoomNumber String number,
        @FutureOrPresent LocalDate checkIn,
        @Future LocalDate checkOut) {
}
