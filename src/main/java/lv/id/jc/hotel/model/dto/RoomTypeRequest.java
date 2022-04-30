package lv.id.jc.hotel.model.dto;

import lv.id.jc.hotel.validator.RoomTypeName;

import javax.validation.constraints.NotBlank;

public record RoomTypeRequest(@RoomTypeName String name, @NotBlank String description) {
}
