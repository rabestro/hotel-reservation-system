package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record RoomServiceImpl(RoomRepository roomRepository) implements RoomService {

    @Override
    public void add(Room room) {
        roomRepository().save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository().findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository().findById(id);
    }
}
