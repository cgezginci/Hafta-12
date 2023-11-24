package dev.patika.library2.dto;

import dev.patika.library2.entities.BookBorrowwing;
import dev.patika.library2.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

private int id;
private String name;
private int publicationYear;
private int stock;
private String authorName;
private String publisherName;




}
