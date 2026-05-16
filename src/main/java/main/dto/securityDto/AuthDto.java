package main.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.dto.PersonDto;

@Getter
@Setter
@AllArgsConstructor
public class AuthDto {
    private String token;
    private PersonDto person;

    @Override
    public String toString() {
        return "Authenticated user{" +
                "token='" + token + '\'' +
                ", person=" + person +
                '}';
    }
}
