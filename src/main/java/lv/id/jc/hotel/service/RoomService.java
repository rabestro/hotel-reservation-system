package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    void add(Room room);

    List<Room> findAll();
}
