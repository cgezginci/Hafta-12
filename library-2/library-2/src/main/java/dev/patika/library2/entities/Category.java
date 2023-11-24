package dev.patika.library2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "serial")
    private int id; // category_id

    @Column(name = "category_name", length = 100, nullable = false)
    private String name; // category_name

    @Column(name = "description", length = 255)
    private String description; // description

    @JsonIgnore
    @ManyToMany(mappedBy = "categoryList" , cascade = CascadeType.REMOVE)
    private List<Book> bookList;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
