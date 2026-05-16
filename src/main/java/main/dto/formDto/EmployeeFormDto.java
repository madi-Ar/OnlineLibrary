package main.dto.formDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeFormDto {
    @NotBlank(message = "First name should not be null or empty")
    @Size(min = 2, max = 15, message = "First name length must be between 2 and 15 characters")
    private String firstName;

    @NotBlank(message = "Last name should not be null or empty")
    @Size(min = 2, max = 15, message = "Last name length must be between 2 and 15 characters")
    private String lastName;

    @NotBlank(message = "Phone should not be empty or null")
    @Size(min = 11, max = 12, message = "Phone should contains 11 or 12 digits")
    @Pattern(regexp = "\\+?[0-9]{11,12}", message = "Phone should contains only digits and can starting with \"\\+\" character")
    private String phone;

    @NotBlank(message = "Email should not be empty or null")
    @Email(message = "Not valid email")
    private String email;

    @NotBlank(message = "Role should not be empty or null")
    @Pattern(regexp = "EMPLOYEE|ADMIN", message = "Role should be only:\"EMPLOYEE\" or \"ADMIN\"")
    private String role;

    @NotBlank(message = "Password should not be empty or null")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

}
