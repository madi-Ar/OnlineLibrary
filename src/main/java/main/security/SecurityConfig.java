package main.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final UserDetailsServerImpl userDetailsServer;
    private final EmployeeDetailsServiceImpl employeeDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registration", "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                        .requestMatchers( "/books/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/records/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers("/records/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employees/*").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers("/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cards/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers("/cards/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/*").hasAnyRole("USER","EMPLOYEE","ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("EMPLOYEE", "ADMIN")
                        .anyRequest().authenticated()
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

    @Bean
    public DaoAuthenticationProvider userProvider(UserDetailsServerImpl userDetailsServer){
        return new DaoAuthenticationProvider(userDetailsServer);
    }

    @Bean
    public DaoAuthenticationProvider employeeProvider(){
        return new DaoAuthenticationProvider(employeeDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }
}
