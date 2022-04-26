package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.RoomDetails;
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
    List<RoomDetails> rooms() {
        return roomService().findAll().stream()
                .map(room -> new RoomDetails(room.getNumber(), room.getDescription()))
                .toList();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid RoomDetails details) {
        roomService().findByNumber(details.number())
                .ifPresent(room -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "The room with number " + room.getNumber() + " already exists");
                });
        updateRoom(new Room(), details);
    }

    @GetMapping("{id}")
    public Room get(@PathVariable Long id) {
        return getRoom(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService().delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid RoomDetails details, @PathVariable Long id) {
        updateRoom(getRoom(id), details);
    }

    private Room getRoom(Long id) {
        return roomService().get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

    private void updateRoom(Room room, RoomDetails details) {
        room.setNumber(details.number());
        room.setDescription(details.description());
        roomService().save(room);
    }
}
