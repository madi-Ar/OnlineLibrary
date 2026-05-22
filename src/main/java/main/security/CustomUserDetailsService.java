package main.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import main.entity.Employee;
import main.entity.User;
import main.repository.EmployeeRepository;
import main.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmailIgnoreCase(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Set<GrantedAuthority> roles = Collections.singleton(user.getRole().toAuthority());
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    roles
            );
        }

        Optional<Employee> employeeOpt = employeeRepository.findByEmailIgnoreCase(username);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            Set<GrantedAuthority> roles = Collections.singleton(employee.getRole().toAuthority());
            return new org.springframework.security.core.userdetails.User(
                    employee.getEmail(),
                    employee.getPassword(),
                    roles
            );
        }

        throw new UsernameNotFoundException("User or Employee with email: " + username + " not found");
    }
}

