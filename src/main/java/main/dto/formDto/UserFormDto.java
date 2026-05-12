package main.dto.formDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {
    @NotBlank(message = "Name should not be null or empty")
    @Size(min = 2,max = 15, message = "Name must be between 2 and 15 characters")
    private String username;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 100, message = "Age must be less than 100")
    private int age;

    @Pattern(regexp = "man|woman", message = "Gender must be man or woman")
    private String gender;

    @NotBlank(message = "email should not be null or empty")
    @Email(message = "not valid email")
    private String email;

    @NotBlank(message = "Password should not be empty or null")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
