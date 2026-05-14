package main;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    USER,
    EMPLOYEE,
    ADMIN;

    public SimpleGrantedAuthority toAuthority(){
        return new SimpleGrantedAuthority("ROLE_"+this.name());
    }
}
