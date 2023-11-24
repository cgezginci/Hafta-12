package dev.patika.library2.dao;

import dev.patika.library2.entities.BookBorrowwing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface BookBorrowwingRepo extends JpaRepository<BookBorrowwing , Integer> {

}
