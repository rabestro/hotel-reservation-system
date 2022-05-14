package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.User;
import lv.id.jc.hotel.model.dto.Credentials;
import lv.id.jc.hotel.repository.UserRepository;
import lv.id.jc.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findFirstByEmailIgnoreCase(email);
    }

    @Override
    public void createUser(Credentials credentials, User.Role role) {
        var user = new User(role, credentials.name(), credentials.email(), encoder.encode(credentials.password()));
        userRepository.save(user);
    }
}
