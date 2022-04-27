package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.validator.RoomNumber;

public record RoomDetails(@RoomNumber String number, String description) {
}
