package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;

import java.util.Optional;

public interface UserService {

    /**
     * Creates a new user with role.
     *
     * @param credentials of the new user
     */
    void createUser(Credentials credentials, User.Role role);

    Optional<User> findByEmail(String email);
}
