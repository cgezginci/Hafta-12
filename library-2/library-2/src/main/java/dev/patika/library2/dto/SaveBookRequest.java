package dev.patika.library2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.patika.library2.entities.Author;
import dev.patika.library2.entities.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBookRequest {

    private String name;
    private int publicationYear;
    private int stock ;
    private int book_author_id;
    private int book_publisher_id;
    private List<Integer> categoryIds;


}