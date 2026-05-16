package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.BookDto;
import main.entity.Book;
import main.exceptions.BookException;
import main.repository.BookRepository;
import main.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BorrowRecordRepository recordRepository;

    @Transactional
    public BookDto create(BookDto bookDto){
        Book book = BookDto.mapToEntity(bookDto);
        if(bookDto.getRecordIds() != null){
            book.setRecords(recordRepository.findAllById(bookDto.getRecordIds()));
        }
        return BookDto.mapToDto(bookRepository.save(book));
    }

    public List<BookDto> findAll(){
        return bookRepository.findAll()
                .stream()
                .map(BookDto::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<BookDto> findById(Long id){
        return bookRepository.findById(id)
                .map(BookDto::mapToDto);
    }

    @Transactional
    public BookDto changeTitle(Long id, String title){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException(id));
        book.setTitle(title);
        return BookDto.mapToDto(bookRepository.save(book));
    }

    @Transactional
    public BookDto changeAuthor(Long id, String author){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException(id));
        book.setAuthor(author);
        return BookDto.mapToDto(bookRepository.save(book));
    }

    @Transactional
    public BookDto changeYear(Long id, int year){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookException(id));
        book.setYear(year);
        return BookDto.mapToDto(bookRepository.save(book));
    }

    @Transactional
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}
