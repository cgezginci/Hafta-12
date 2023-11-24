package dev.patika.library2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookBorrowwing {
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private int bookborrowing_book_id;
}
