package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.repository.UserRepository;
import lv.id.jc.hotel.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) implements UserService {

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository().findFirstByEmailIgnoreCase(email);
    }

    @Override
    public void createUser(Credentials credentials, User.Role role) {
        var user = new User();
        user.setName(credentials.name());
        user.setEmail(credentials.email());
        user.setPassword(encoder().encode(credentials.password()));
        user.setRole(role);
        user.setEnabled(true);
        userRepository().save(user);
    }
}
