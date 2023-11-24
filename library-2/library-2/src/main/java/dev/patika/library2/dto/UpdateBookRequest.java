package dev.patika.library2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {
    private int id;
    private String bookName;
    private int stock;
    private int book_publisher_id;
}