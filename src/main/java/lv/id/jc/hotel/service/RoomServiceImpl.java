package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record RoomServiceImpl(RoomRepository roomRepository) implements RoomService {

    @Override
    public void save(Room room) {
        roomRepository().save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository().findAll();
    }

    @Override
    public Optional<Room> get(Long id) {
        return roomRepository().findById(id);
    }

    @Override
    public void delete(Long id) {
        roomRepository().deleteById(id);
//        var recipe = roomRepository().findById(id).orElseThrow(NoSuchElementException::new);
//        roomRepository().delete(recipe);
    }
}
