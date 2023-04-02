package lv.id.jc.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.repository.UserRepository;
import lv.id.jc.hotel.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findFirstByEmailIgnoreCase(email);
    }

    @Override
    public User createUser(Credentials credentials, User.Role role) {
        var user = new User(role, credentials.name(), credentials.email(), encoder.encode(credentials.password()));
        return userRepository.save(user);
    }
}
