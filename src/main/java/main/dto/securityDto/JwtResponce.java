package main.dto.securityDto;

import lombok.Getter;

@Getter
public class JwtResponce {
    private String token;

    public JwtResponce(String token) {
        this.token = token;
    }
}
