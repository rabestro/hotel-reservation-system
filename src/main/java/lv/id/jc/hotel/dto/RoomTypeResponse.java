package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.model.RoomType;

public record RoomTypeResponse(Long id, String name, String description) {
    public RoomTypeResponse(RoomType roomType) {
        this(roomType.getId(), roomType.getName(), roomType.getDescription());
    }
}
