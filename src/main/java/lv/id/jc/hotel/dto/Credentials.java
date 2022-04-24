package lv.id.jc.hotel.dto;

import lv.id.jc.hotel.validator.StrongPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record Credentials(
        @NotBlank String name,
        @Email String email,
        @StrongPassword String password
) {
}
