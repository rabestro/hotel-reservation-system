package lv.id.jc.hotel.controller;

import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

abstract class AbstractRegistrationController {
    private final UserService userService;

    AbstractRegistrationController(UserService userService) {
        this.userService = userService;
    }

    void checkEmailExists(Credentials credentials) {
        if (getUserService().isEmailExist(credentials.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the user already registered.");
        }
    }

    UserService getUserService() {
        return userService;
    }
}
