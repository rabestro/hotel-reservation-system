package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findFirstByNumber(String number);

//    Optional<Room> findFirstByTypeAnd

}
