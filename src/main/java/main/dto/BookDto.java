package main.dto;

import lombok.Getter;
import lombok.Setter;
import main.entity.Book;
import main.entity.BorrowRecord;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private int year;
    private List<Long> recordIds;

    public static BookDto mapToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setYear(book.getYear());
        if(book.getRecords() != null){
            List<Long> recordIds = new ArrayList<>();
            for(BorrowRecord record : book.getRecords()){
                recordIds.add(record.getId());
            }
            bookDto.setRecordIds(recordIds);
        }
        return bookDto;
    }

    public static Book mapToEntity(BookDto bookDto){
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setYear(bookDto.getYear());
        return book;
    }
}
