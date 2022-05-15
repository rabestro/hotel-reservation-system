package lv.id.jc.hotel.model.dto;

import lv.id.jc.hotel.validator.StrongPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record Credentials(
        @NotBlank String name,
        @NotNull @Email String email,
        @StrongPassword String password
) {
}
