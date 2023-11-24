package dev.patika.library2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "Books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id" , columnDefinition = "serial")
    private int id; //book_id


    @Column(name = "book_name" , length = 100 , nullable = false)
    private String name; //book_name

    @Column(name = "publication_year")
    private int publicationYear; //publication_year

    @Column(name = "book_stock" , nullable = false)
    private int stock; //book_stock

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "book_author_id" , referencedColumnName = "author_id")
    private Author author;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_categories" ,
            joinColumns = {
                    @JoinColumn(name = "bookcategories_book_id")
            },
            inverseJoinColumns = {@JoinColumn(name = "bookcategories_category_id")})
    private List<Category> categoryList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "book_publisher_id" , referencedColumnName = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.REMOVE)
    private List<BookBorrowwing> bookBorrowwingList;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationYear=" + publicationYear +
                ", stock=" + stock +
                ", author=" + author +
                ", categoryList=" + categoryList +
                ", publisher=" + publisher +
                '}';
    }
}
