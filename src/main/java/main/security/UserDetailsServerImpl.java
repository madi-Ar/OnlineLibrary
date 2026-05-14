package main.security;

import lombok.RequiredArgsConstructor;
import main.entity.User;
import main.exceptions.UserException;
import main.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserDetailsServerImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UserException {
        User user = userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UserException("User with email: "+username+" not found"));
        Set<GrantedAuthority> roles = Collections.singleton(user.getUserRole().toAuthority());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }
}
