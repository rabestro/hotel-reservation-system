package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.Credentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {

    @PostMapping("/admin/employee/new")
    public void register(@RequestBody @Valid Credentials credentials) {
    }
}
