package main.dto;

import lombok.Getter;
import lombok.Setter;
import main.entity.Book;
import main.entity.User;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private int age;
    private String gender;
    private Long cardId;

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        if(user.getGender()){
            userDto.setGender("man");
        } else {
            userDto.setGender("woman");
        }
        if (user.getCard() != null) {
            userDto.setCardId(user.getCard().getId());
        }
        return userDto;
    }

    public static User mapToUserEntity(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        if (userDto.getGender().equals("man")){
            user.setGender(true);
        } else if (userDto.getGender().equals("woman")){
            user.setGender(false);
        }
        return user;
    }
}
