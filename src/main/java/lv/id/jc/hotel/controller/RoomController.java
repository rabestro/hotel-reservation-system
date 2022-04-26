package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        roomService().save(room);
    }

    @GetMapping("{id}")
    public Room get(@PathVariable Long id) {
        return roomService().get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService().delete(id);
    }

}
