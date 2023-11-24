package dev.patika.library2.business.abstracts;

import dev.patika.library2.dto.SaveBookRequest;
import dev.patika.library2.dto.UpdateBookRequest;
import dev.patika.library2.entities.Book;

import java.util.List;

public interface IBookService {
    Book getById (int id);

    Book save(Book book);

    Book update(Book book);

    void delete (int id);

    List<Book> findAll();

    String getBookSuccessMessage(Book book);

    String getBookErrorMessage();

    String getBookDeleteSuccessMessage(int id);

    String getBookDeleteErrorMessage();
}
