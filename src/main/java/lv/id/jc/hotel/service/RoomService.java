package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoomService {
    void add(Room room);

    List<Room> findAll();

    /**
     * Retrieves a room by its id.
     *
     * @param id must not be {@literal null}.
     * @return the room entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<Room> findById(Long id);
}
