package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid Credentials credentials) {
        userService.createUser(credentials, User.Role.CUSTOMER);
    }

}
