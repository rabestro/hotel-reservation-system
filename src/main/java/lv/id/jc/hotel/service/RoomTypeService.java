package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.model.dto.RoomTypeRequest;
import lv.id.jc.hotel.model.dto.RoomTypeResponse;

import java.util.List;

public interface RoomTypeService {

    RoomType createRoomType(RoomTypeRequest request);

    List<RoomTypeResponse> findAll();
}
