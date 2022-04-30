package lv.id.jc.hotel.model.dto;

import lv.id.jc.hotel.model.Room;

public record RoomResponse(Long id, String number, String type) {
    public RoomResponse(Room room) {
        this(room.getId(), room.getNumber(), room.getType().getName());
    }
}
