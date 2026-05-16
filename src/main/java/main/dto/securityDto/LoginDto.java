package main.dto.securityDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "email should not be null or empty")
    @Email(message = "not valid email")
    private String email;

    @NotBlank(message = "Password should not be empty or null")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
