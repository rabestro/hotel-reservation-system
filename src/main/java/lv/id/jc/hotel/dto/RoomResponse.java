package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.model.Room;

public record RoomResponse(Long id, String number, String roomType) {
    public RoomResponse(Room room) {
        this(room.getId(), room.getNumber(), room.getType().getName());
    }
}
