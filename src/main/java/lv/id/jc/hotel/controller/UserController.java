package lv.id.jc.hotel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.service.UserService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Tag(
        name = "Employee registration",
        description = """
                Registration of new employees of the hotel.
                This request is available only to registered employees of the hotel.
                """
)
@RepositoryRestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Register an employee",
            description = "add new employee of the hotel",
            security = @SecurityRequirement(
                    name = "BasicAuthentication"
            ),
            parameters = @Parameter(
                    name = "credentials",
                    required = true
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    @PostMapping("users")
    @Secured("ROLE_EMPLOYEE")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User register(@RequestBody @Valid Credentials credentials) {
        return userService.createUser(credentials, User.Role.EMPLOYEE);
    }

}
