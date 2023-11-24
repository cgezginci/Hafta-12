package dev.patika.library2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Publishers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", columnDefinition = "serial")
    private int id; // publisher_id

    @Column(name = "publisher_name", length = 100, nullable = false)
    private String name; // publisher_name

    @Column(name = "establishment_year")
    private int establishmentYear; // establishment_year

    @Column(name = "address", length = 255)
    private String address; // address

    @OneToMany(mappedBy = "publisher" ,  cascade = CascadeType.REMOVE)
    private List<Book> bookList;

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", establishmentYear=" + establishmentYear +
                ", address='" + address + '\'' +
                '}';
    }
}
