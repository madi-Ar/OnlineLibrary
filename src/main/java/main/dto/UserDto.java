package main.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import main.entity.Book;
import main.entity.User;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;

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

    private Long cardId;

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());
        if (user.getCard() != null) {
            userDto.setCardId(user.getCard().getId());
        }
        return userDto;
    }

    public static User mapToUserEntity(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
