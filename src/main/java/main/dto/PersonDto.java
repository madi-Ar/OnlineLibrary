package main.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private Long id;

    @NotBlank(message = "email should not be null or empty")
    @Email(message = "not valid email")
    private String email;

    public PersonDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public PersonDto(){}
}
