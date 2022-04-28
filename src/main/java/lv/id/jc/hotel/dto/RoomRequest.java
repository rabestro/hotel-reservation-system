package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.validator.RoomNumber;

public record RoomRequest(@RoomNumber String number, Long typeId) {
}
