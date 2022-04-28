package lv.id.jc.hotel.service;

import lv.id.jc.hotel.dto.RoomTypeDetails;
import lv.id.jc.hotel.model.RoomType;

import java.util.List;

public interface RoomTypeService {

    RoomType createRoomType(RoomTypeDetails details);

    List<RoomType> findAll();
}
