package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findFirstByNumber(String number);
}
