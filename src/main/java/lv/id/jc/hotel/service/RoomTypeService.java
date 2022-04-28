package lv.id.jc.hotel.service;

import lv.id.jc.hotel.dto.RoomTypeRequest;
import lv.id.jc.hotel.model.RoomType;

import java.util.List;

public interface RoomTypeService {

    RoomType createRoomType(RoomTypeRequest request);

    List<RoomType> findAll();
}
