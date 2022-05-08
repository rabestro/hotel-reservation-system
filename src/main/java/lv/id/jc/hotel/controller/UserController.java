package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public @ResponseBody ResponseEntity<?> register(@RequestBody @Valid Credentials credentials) {
        userService.createEmployee(credentials);
        return ResponseEntity.ok(null);
    }

}
