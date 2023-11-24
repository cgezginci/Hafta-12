package dev.patika.library2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "BookBorrowings")
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowwing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id", columnDefinition = "serial")
    private int id; // borrowing_id

    @Column(name = "borrower_name", length = 100, nullable = false)
    private String borrowerName; // borrower_name

    @Column(name = "borrowing_date" , nullable = false)
    private LocalDate borrowingDate; // borrowing_date

    @Column(name = "return_date")
    private LocalDate returnDate; // return_date

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookborrowing_book_id" , referencedColumnName = "book_id")
    private Book book;

    @Override
    public String toString() {
        return "BookBorrowwing{" +
                "id=" + id +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                '}';
    }
}
