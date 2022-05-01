package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.Room;
import lv.id.jc.hotel.model.dto.RoomRequest;
import lv.id.jc.hotel.model.dto.RoomResponse;
import lv.id.jc.hotel.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public record RoomController(RoomService roomService) {

    @GetMapping
    List<RoomResponse> rooms() {
        return roomService().findAll().stream()
                .map(RoomResponse::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<RoomResponse> add(@RequestBody @Valid RoomRequest details) {
        var response = new RoomResponse(roomService().add(details));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public Room update(@RequestBody @Valid RoomRequest details, @PathVariable Long id) {
        return roomService().update(id, details);
    }

    private Room getRoom(Long id) {
        return roomService().get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }

}
