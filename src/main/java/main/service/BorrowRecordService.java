package main.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import main.dto.BorrowRecordDto;
import main.entity.BorrowRecord;
import main.repository.BookRepository;
import main.repository.BorrowRecordRepository;
import main.repository.LibraryCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowRecordService {
    private final BorrowRecordRepository recordRepository;
    private final LibraryCardRepository cardRepository;
    private final BookRepository bookRepository;

    @Transactional
    public BorrowRecordDto create(BorrowRecordDto recordDto){
        BorrowRecord record = BorrowRecordDto.mapToRecordEntity(recordDto);
        if(recordDto.getCardId() != null){
            record.setCard(cardRepository.findById(recordDto.getCardId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        if (recordDto.getBookId()!=null){
            record.setBook(bookRepository.findById(recordDto.getBookId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return BorrowRecordDto.mapToRecordDto(recordRepository.save(record));
    }

    public List<BorrowRecordDto> findAll(){
        return recordRepository.findAll()
                .stream()
                .map(BorrowRecordDto::mapToRecordDto)
                .collect(Collectors.toList());
    }

    public Optional<BorrowRecordDto> findById(Long id){
        return recordRepository.findById(id)
                .map(BorrowRecordDto::mapToRecordDto);
    }

    @Transactional
    public BorrowRecordDto changeBorrowDate(Long id){
        BorrowRecord record = recordRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        record.setBorrowDate(LocalDate.now());
        recordRepository.save(record);
        return BorrowRecordDto.mapToRecordDto(record);
    }

    @Transactional
    public void deleteById(Long id){
        recordRepository.deleteById(id);
    }
}
