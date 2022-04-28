package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.validator.ExistingRoomType;
import lv.id.jc.hotel.validator.RoomNumber;

public record RoomDetails(@RoomNumber String number, @ExistingRoomType Long typeId) {
}
