package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Checks if the user is registered with this email address
     *
     * @param email address to check
     * @return true if there is a user with the given address
     */
    boolean isEmailExist(String email);

    /**
     * Creates a new user with employee rights.
     *
     * @param credentials of the new employee
     */
    void createEmployee(Credentials credentials);

    /**
     * Creates a new user with customer rights.
     *
     * @param credentials of the new customer
     */
    void createCustomer(Credentials credentials);

    /**
     * Find all users with employee rights
     *
     * @return List of employees
     */
    List<User> findEmployees();

    Optional<User> findByEmail(String email);
}
