package lv.id.jc.hotel.service;

import lv.id.jc.hotel.dto.RoomTypeDetails;
import lv.id.jc.hotel.model.RoomType;

public interface RoomTypeService {

    RoomType createRoomType(RoomTypeDetails details);
}
