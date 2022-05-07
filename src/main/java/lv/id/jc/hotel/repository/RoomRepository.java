package lv.id.jc.hotel.repository;

import lv.id.jc.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @RestResource(path = "byNumber")
    Optional<Room> findFirstByNumber(String number);
}
