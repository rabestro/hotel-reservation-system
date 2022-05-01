package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getEmployees() {
        return userService.findEmployees();
    }
}
