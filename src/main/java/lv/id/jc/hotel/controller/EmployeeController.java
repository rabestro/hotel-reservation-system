package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.Credentials;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.repository.UserRepository;
import lv.id.jc.hotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public record EmployeeController(UserService userService) {

    @PostMapping("/new")
    public void register(@RequestBody @Valid Credentials credentials) {
        if (userService().isEmailExist(credentials.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the user already registered.");
        }
        userService().createEmployee(credentials);
    }

    @GetMapping
    public List<User> getEmployees() {
        return userService().findEmployees();
    }
}
