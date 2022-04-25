package lv.id.jc.hotel.service;

import lv.id.jc.hotel.dto.Credentials;
import lv.id.jc.hotel.model.Role;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) implements UserService {

    @Override
    public boolean isEmailExist(String email) {
        return findByEmail(email).isPresent();
    }

    @Override
    public void createEmployee(Credentials credentials) {
        createUser(credentials, Role.EMPLOYEE);
    }

    @Override
    public void createCustomer(Credentials credentials) {
        createUser(credentials, Role.CUSTOMER);
    }

    @Override
    public List<User> findEmployees() {
        return userRepository().findByRole(Role.EMPLOYEE);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository().findByEmail(email).stream().findFirst();
    }

    private void createUser(Credentials credentials, Role role) {
        var user = new User();
        user.setName(credentials.name());
        user.setEmail(credentials.email());
        user.setPassword(encoder().encode(credentials.password()));
        user.setRole(role);
        userRepository().save(user);
    }
}
