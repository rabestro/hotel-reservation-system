package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoomServiceImpl(RoomRepository roomRepository) implements RoomService {

    @Override
    public void add(Room room) {

    }

    @Override
    public List<Room> findAll() {
        return roomRepository().findAll();
    }
}
