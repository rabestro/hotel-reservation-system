package lv.id.jc.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findFirstByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    }
}
