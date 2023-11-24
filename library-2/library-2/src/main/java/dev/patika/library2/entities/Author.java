package dev.patika.library2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "Authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "serial")
    private int id; // author_id

    @Column(name = "author_name", length = 100, nullable = false)
    private String name; // author_name

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private LocalDate birthday; // birthday

    @Column(name = "country", length = 50)
    private String country; // country

    @JsonIgnore
    @OneToMany(mappedBy = "author" , cascade = CascadeType.REMOVE)
    private List<Book> bookList;


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                '}';
    }
}
