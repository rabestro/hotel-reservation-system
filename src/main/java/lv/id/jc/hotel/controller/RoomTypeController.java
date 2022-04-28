package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.RoomTypeRequest;
import lv.id.jc.hotel.dto.RoomTypeResponse;
import lv.id.jc.hotel.service.RoomTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/type")
public record RoomTypeController(RoomTypeService service) {

    @PostMapping
    public ResponseEntity<RoomTypeResponse> add(@RequestBody @Valid RoomTypeRequest request) {
        var response = new RoomTypeResponse(service().createRoomType(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<RoomTypeResponse> findAll() {
        return service().findAll().stream()
                .map(RoomTypeResponse::new)
                .toList();
    }

}
