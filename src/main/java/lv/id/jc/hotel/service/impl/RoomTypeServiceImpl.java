package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.dto.RoomTypeRequest;
import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.service.RoomTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoomTypeServiceImpl(RoomTypeRepository repository) implements RoomTypeService {

    @Override
    public RoomType createRoomType(RoomTypeRequest request) {
        var roomType = new RoomType();
        roomType.setName(request.name());
        roomType.setDescription(request.description());
        return repository().save(roomType);
    }

    @Override
    public List<RoomType> findAll() {
        return repository().findAll();
    }
}
