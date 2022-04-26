package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.Credentials;
import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public record RoomController(RoomService roomService) {

    @GetMapping
    List<Room> rooms() {
        return roomService().findAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid Room room) {
        roomService().add(room);
    }

}
