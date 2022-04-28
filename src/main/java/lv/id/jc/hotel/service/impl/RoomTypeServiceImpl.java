package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.dto.RoomTypeDetails;
import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.service.RoomTypeService;
import org.springframework.stereotype.Service;

@Service
public record RoomTypeServiceImpl(RoomTypeRepository repository) implements RoomTypeService {

    @Override
    public RoomType createRoomType(RoomTypeDetails details) {
        var roomType = new RoomType();
        roomType.setName(details.name());
        roomType.setDescription(details.description());
        return repository().save(roomType);
    }
}
