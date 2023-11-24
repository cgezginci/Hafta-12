package dev.patika.library2.business.concretes;

import dev.patika.library2.business.abstracts.IBookService;
import dev.patika.library2.dao.BookRepo;
import dev.patika.library2.dto.SaveBookRequest;
import dev.patika.library2.dto.UpdateBookRequest;
import dev.patika.library2.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements IBookService {

    @Autowired
    private BookRepo bookRepo;


    @Override
    public Book getById(int id) {
        return this.bookRepo.findById(id).orElseThrow();
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        return this.bookRepo.save(book);
    }


    @Override
    public void delete(int id) {

        this.bookRepo.delete(this.getById(id));
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepo.findAll();
    }

    @Override
    public String getBookSuccessMessage(Book book) {
        return "İşlem başarılı. Kitap ID: " + book.getId();
    }

    @Override
    public String getBookErrorMessage() {
        return "İşlem gerçekleşmedi , bir hata oluştu.";
    }

    @Override
    public String getBookDeleteSuccessMessage(int id) {
        return "Kitap başarıyla silindi. Yazar ID: " + id;
    }

    @Override
    public String getBookDeleteErrorMessage() {
        return "Kitap silinemedi. Lütfen tekrar deneyin.";
    }
}
