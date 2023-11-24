package dev.patika.library2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookBorrowwingDto {
    private int id;
    private String borrowerName;
    private LocalDate returnDate;
}
