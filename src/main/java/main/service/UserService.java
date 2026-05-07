package main.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.UserDto;
import main.entity.LibraryCard;
import main.entity.User;
import main.repository.LibraryCardRepository;
import main.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LibraryCardRepository libraryCardRepository;
    private final LibraryCardService cardService;

    @Transactional
    public UserDto create(UserDto userDto){
        LibraryCard card = new LibraryCard();
        card.setDateOfCreation(LocalDate.now());
        User user = UserDto.mapToUserEntity(userDto);
        user.setCard(card);
        return UserDto.mapToUserDto(userRepository.save(user));
    }

    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserDto::mapToUserDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findById(Long id){
        return userRepository.findById(id)
                .map(UserDto::mapToUserDto);
    }

    @Transactional
    public UserDto changeName(Long id, String name){
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        user.setUsername(name);
        return UserDto.mapToUserDto(userRepository.save(user));
    }

    @Transactional
    public UserDto changeAge(Long id, int age){
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        user.setAge(age);
        return UserDto.mapToUserDto(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
