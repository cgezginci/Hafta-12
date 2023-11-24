package dev.patika.library2.dao;

import dev.patika.library2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book , Integer> {

    @Query("SELECT b FROM Book b JOIN b.categoryList c WHERE c.id = :categoryId")
    List<Book> findBooksByCategoryId(@Param("categoryId") int categoryId);

}
