package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public record RoomServiceImpl(RoomRepository roomRepository) implements RoomService {

    @Override
    public Room save(Room room) {
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
