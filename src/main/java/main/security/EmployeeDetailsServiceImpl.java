package main.security;

import lombok.RequiredArgsConstructor;
import main.entity.Employee;
import main.exceptions.EmployeeException;
import main.repository.EmployeeRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws EmployeeException {
        Employee employee = employeeRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new EmployeeException("Employee with email: "+username+" not found"));
        Set<GrantedAuthority> roles = Collections.singleton(employee.getRole().toAuthority());
        return new User(employee.getEmail(), employee.getPassword(), roles);
    }
}
