package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.LibraryCardDto;
import main.entity.LibraryCard;
import main.exceptions.LibraryCardException;
import main.exceptions.UserException;
import main.repository.BorrowRecordRepository;
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
public class LibraryCardService {
    private final LibraryCardRepository cardRepository;
    private final UserRepository userRepository;
    private final BorrowRecordRepository recordRepository;

    @Transactional
    public LibraryCardDto create(LibraryCardDto cardDto){
        LibraryCard card = LibraryCardDto.mapToCardEntity(cardDto);
        if(cardDto.getUserId() != null){
            card.setUser(userRepository.findById(cardDto.getUserId())
                    .orElseThrow(() -> new UserException(cardDto.getUserId())));
        }
        if(cardDto.getRecordIds() != null){
            card.setBorrowRecords(recordRepository.findAllById(cardDto.getRecordIds()));
        }
        return LibraryCardDto.mapToCardDto(cardRepository.save(card));
    }

    public List<LibraryCardDto> findAll(){
        return cardRepository.findAll()
                .stream()
                .map(LibraryCardDto::mapToCardDto)
                .collect(Collectors.toList());
    }

    public Optional<LibraryCardDto> findById(Long id){
        return cardRepository.findById(id)
                .map(LibraryCardDto::mapToCardDto);
    }

    @Transactional
    public LibraryCardDto changeDateOfCreation(Long id){
        LibraryCard card = cardRepository.findById(id)
                .orElseThrow(() -> new LibraryCardException(id));
        card.setDateOfCreation(LocalDate.now());
        return LibraryCardDto.mapToCardDto(cardRepository.save(card));
    }

    @Transactional
    public void deleteById(Long id){
        cardRepository.deleteById(id);
    }
}
