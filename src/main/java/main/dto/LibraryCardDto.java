package main.dto;

import lombok.Getter;
import lombok.Setter;
import main.entity.BorrowRecord;
import main.entity.LibraryCard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LibraryCardDto {
    private Long id;
    private LocalDate dateOfCreation;
    private LocalDate validityPeriod;
    private Long userId;
    private List<Long> recordIds;

    public static LibraryCardDto mapToCardDto(LibraryCard card){
        LibraryCardDto cardDto = new LibraryCardDto();
        cardDto.setId(card.getId());
        cardDto.setDateOfCreation(LocalDate.now());
        cardDto.setValidityPeriod(card.getDateOfCreation().plusYears(3));
        if(card.getUser() != null) {
            cardDto.setUserId(card.getUser().getId());
        } else {
            cardDto.setUserId(null);
        }
        if (card.getBorrowRecords() != null) {
            List<Long> recordIds = new ArrayList<>();
            for (BorrowRecord borrowRecord : card.getBorrowRecords()) {
                recordIds.add(borrowRecord.getId());
            }
            cardDto.setRecordIds(recordIds);
        }
        return cardDto;
    }

    public static LibraryCard mapToCardEntity(LibraryCardDto cardDto){
        LibraryCard card = new LibraryCard();
        card.setDateOfCreation(cardDto.getDateOfCreation());
        return card;
    }
}
