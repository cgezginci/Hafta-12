package dev.patika.library2.business.abstracts;

import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.BookBorrowwing;

import java.util.List;

public interface IBookBorrowwingService {

    BookBorrowwing getById (int id);

    BookBorrowwing save (BookBorrowwing bookBorrowwing);

    BookBorrowwing update (BookBorrowwing bookBorrowwing);

    void delete (int id);

    List<BookBorrowwing> findAll();

    String getBookBorrowwingSuccessMessage(BookBorrowwing bookBorrowwing);

    String getBookBorrowwingErrorMessage();

    String getBookBorrowwingDeleteSuccessMessage(int id);

    String getBookBorrowwingDeleteErrorMessage();

    String getBorrowError();


}
