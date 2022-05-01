package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.RoomTypeRequest;
import lv.id.jc.hotel.model.dto.RoomTypeResponse;
import lv.id.jc.hotel.service.RoomTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/type")
public record RoomTypeController(RoomTypeService service) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomTypeResponse add(@RequestBody @Valid RoomTypeRequest request) {
        return new RoomTypeResponse(service().createRoomType(request));
    }

    @GetMapping
    public List<RoomTypeResponse> findAll() {
        return service().findAll();
    }

}
