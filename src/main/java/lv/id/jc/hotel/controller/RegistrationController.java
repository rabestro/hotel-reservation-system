package lv.id.jc.hotel.controller;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.model.dto.Customer;
import lv.id.jc.hotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Registration of clients in the hotel reservation system.
 */
@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer register(@RequestBody @Valid Credentials credentials) {
        var user = userService.createUser(credentials, User.Role.CUSTOMER);
        return new Customer(user.getName(), user.getEmail());
    }

}
