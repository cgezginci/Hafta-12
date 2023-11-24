package dev.patika.library2.business.concretes;

import dev.patika.library2.business.abstracts.IBookBorrowwingService;
import dev.patika.library2.dao.BookBorrowwingRepo;
import dev.patika.library2.dao.BookRepo;
import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.BookBorrowwing;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBorrowwingManager implements IBookBorrowwingService {

    @Autowired
    private BookBorrowwingRepo bookBorrowwingRepo;

    @Autowired
    private BookRepo bookRepo;

    @Override
    public BookBorrowwing getById(int id) {
        return this.bookBorrowwingRepo.findById(id).orElseThrow();
    }

    @Override
    public BookBorrowwing save(BookBorrowwing bookBorrowwing) {

        int bookId = bookBorrowwing.getBook().getId();


        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Kitap bulunamadı. ID: " + bookId));


        if (book.getStock() <= 0) {
            throw new RuntimeException("Kitap stokta bulunmamaktadır. Ödünç verme işlemi gerçekleştirilemez.");
        }


        book.setStock(book.getStock() - 1);

        bookRepo.save(book);

        // BookBorrowwing nesnesini veritabanına kaydet
        return bookBorrowwingRepo.save(bookBorrowwing);

    }

    @Override
    public BookBorrowwing update(BookBorrowwing bookBorrowwing) {


        return this.bookBorrowwingRepo.save(bookBorrowwing);
    }

    @Override
    public void delete(int id) {
        // İlgili ödünç alma işlemini getir
        BookBorrowwing bookBorrowwing = this.getById(id);

        // Ödünç alma işlemi varsa devam et
        if (bookBorrowwing != null) {
            // Önce ödünç alınan kitabı getir
            Book book = bookBorrowwing.getBook();

            // Stok artırma işlemi
            book.setStock(book.getStock() + 1);
            bookRepo.save(book);

            // Son olarak ödünç alma işlemini sil
            this.bookBorrowwingRepo.delete(bookBorrowwing);
        }
    }

    @Override
    public List<BookBorrowwing> findAll() {
        return this.bookBorrowwingRepo.findAll();
    }

    @Override
    public String getBookBorrowwingSuccessMessage(BookBorrowwing bookBorrowwing) {
        return "İşlem başarılı. ID: " + bookBorrowwing.getId();
    }

    @Override
    public String getBookBorrowwingErrorMessage() {
        return "İşlem gerçekleşmedi , bir hata oluştu.";
    }

    @Override
    public String getBookBorrowwingDeleteSuccessMessage(int id) {
        return "Ödünç verme başarıyla silindi. Ödünç verme ID: " + id;
    }

    @Override
    public String getBookBorrowwingDeleteErrorMessage() {
        return "Ödünç verme silinemedi. Lütfen tekrar deneyin.";
    }

    @Override
    public String getBorrowError() {
        return "Kitap stokta yoktur";
    }


}
