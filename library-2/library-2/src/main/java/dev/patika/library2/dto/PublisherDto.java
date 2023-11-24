package dev.patika.library2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {

    private int id;
    private String name;
    private int establishment_year;
    private List<String> bookNames;

}
