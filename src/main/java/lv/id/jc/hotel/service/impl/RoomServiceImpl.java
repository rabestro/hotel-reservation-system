package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.dto.RoomRequest;
import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record RoomServiceImpl(
        RoomRepository roomRepository,
        RoomTypeRepository typeRepository) implements RoomService {

    @Override
    public Room save(Room room) {
        return roomRepository().save(room);
    }

    @Override
    public Room add(RoomRequest details) {
        var room = new Room();
        room.setNumber(details.number());
        var type = typeRepository().getById(details.typeId());
        room.setType(type);
        return roomRepository().save(room);
    }

    @Override
    public Room update(Long id, RoomRequest details) {
        var room = roomRepository().getById(id);
        var type = typeRepository().getById(details.typeId());
        room.setNumber(details.number());
        room.setType(type);
        return roomRepository().save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository().findAll();
    }

    @Override
    public Optional<Room> findByNumber(String number) {
//        Objects.requireNonNull(number);
        return roomRepository().findFirstByNumber(number);
    }

    @Override
    public Optional<Room> get(Long id) {
        return roomRepository().findById(id);
    }

    @Override
    public void delete(Room room) {
        roomRepository().delete(room);
    }
}
