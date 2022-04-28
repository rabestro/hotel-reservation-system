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
                .map(room -> new RoomDetails(room.getNumber(), room.getType().getId()))
                .toList();
    }

    @PostMapping
    public Room add(@RequestBody @Valid RoomDetails details) {
        roomService().findByNumber(details.number())
                .ifPresent(room -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "The room with number " + room.getNumber() + " already exists");
                });
        return roomService().add(details);
    }

    @GetMapping("{id}")
    public Room get(@PathVariable Long id) {
        return getRoom(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService().delete(getRoom(id));
    }

    @PutMapping("{id}")
    public Room update(@RequestBody @Valid RoomDetails details, @PathVariable Long id) {
        return roomService().update(id, details);
    }

    private Room getRoom(Long id) {
        return roomService().get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

}
