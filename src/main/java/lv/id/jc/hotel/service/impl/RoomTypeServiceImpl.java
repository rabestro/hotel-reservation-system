package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.model.dto.RoomTypeRequest;
import lv.id.jc.hotel.model.dto.RoomTypeResponse;
import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.service.RoomTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoomTypeServiceImpl(RoomTypeRepository repository) implements RoomTypeService {

    @Override
    public RoomType createRoomType(RoomTypeRequest request) {
        var roomType = new RoomType()
                .setName(request.name())
                .setDescription(request.description());
        return repository().save(roomType);
    }

    @Override
    public List<RoomTypeResponse> findAll() {
        return repository().findAll().stream()
                .map(RoomTypeResponse::new)
                .toList();
    }
}
