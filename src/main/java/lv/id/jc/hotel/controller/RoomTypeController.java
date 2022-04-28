package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.RoomTypeDetails;
import lv.id.jc.hotel.model.RoomType;
import lv.id.jc.hotel.service.RoomTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/type")
public record RoomTypeController(RoomTypeService service) {

    @PostMapping
    public RoomType register(@RequestBody @Valid RoomTypeDetails details) {
        return service().createRoomType(details);
    }

}
