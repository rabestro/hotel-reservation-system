package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.dto.Credentials;
import lv.id.jc.hotel.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeRegistrationController extends AbstractRegistrationController {

    EmployeeRegistrationController(UserService userService) {
        super(userService);
    }

    @PostMapping("/new")
    public void register(@RequestBody @Valid Credentials credentials) {
        checkEmailExists(credentials);
        getUserService().createEmployee(credentials);
    }

}
