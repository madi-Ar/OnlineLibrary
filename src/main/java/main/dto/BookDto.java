package main.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Title must not be null")
    @Size(min = 2, max = 100, message = "length must be 2 and 100 characters")
    private String title;

    @NotBlank(message = "Author must not be null")
    @Size(min = 2, max = 100, message = "length must be 2 and 100 characters")
    private String author;

    @Min(value = 1000,message = "Minimum year - 1000")
    @Max(value = 2026, message = "Maximum year - 2026")
    private int year;

    private List<Long> recordIds;

    public static BookDto mapToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
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
