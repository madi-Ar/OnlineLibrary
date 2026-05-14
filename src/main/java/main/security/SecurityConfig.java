package main.security;

import lombok.RequiredArgsConstructor;
import main.entity.User;
import main.exceptions.UserException;
import main.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtFilter jwtFilter;
    private final UserDetailsServerImpl userDetailsServer;
    private final EmployeeDetailsServiceImpl employeeDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                        .requestMatchers( "/books/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/records/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers("/records/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employees/*").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers("/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cards/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers("/cards/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .usernameParameter("email")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout").permitAll()
                )
                .authenticationProvider(userAuthenticationProvider(userDetailsServer, passwordEncoder()))
                .authenticationProvider(employeeAuthenticationProvider(employeeDetailsService, passwordEncoder()))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider(UserDetailsServerImpl userDetailsServerImpl,
                                                                PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsServerImpl);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider employeeAuthenticationProvider(EmployeeDetailsServiceImpl employeeDetailsServiceImpl,
                                                                    PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(employeeDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
