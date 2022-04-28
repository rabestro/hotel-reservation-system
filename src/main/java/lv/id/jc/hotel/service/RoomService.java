package lv.id.jc.hotel.service;

import lv.id.jc.hotel.dto.RoomRequest;
import lv.id.jc.hotel.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoomService {
    /**
     * Save a given room. Use the returned instance for further operations as the
     * add operation might have changed the room entity instance completely.
     *
     * @param room must not be {@literal null}.
     * @return the saved room entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal room entity} is {@literal null}.
     */
    Room save(Room room);

    Room add(RoomRequest details);

    Room update(Long id, RoomRequest details);
    /**
     * Returns all rooms.
     *
     * @return all rooms entities
     */
    List<Room> findAll();

    /**
     * Retrieves a room by its number.
     *
     * @param number must not be {@literal null}.
     * @return the room entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<Room> findByNumber(String number);

    /**
     * Retrieves a room by its id.
     *
     * @param id must not be {@literal null}.
     * @return the room entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<Room> get(Long id);

    /**
     * Deletes the given room.
     *
     * @param room must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal room} is {@literal null}
     */
    void delete(Room room);

}
