package dev.patika.library2.api;

import dev.patika.library2.business.abstracts.IBookService;
import dev.patika.library2.dto.BookDto;
import dev.patika.library2.dto.CategoryDto;
import dev.patika.library2.dto.SaveBookRequest;
import dev.patika.library2.entities.Author;
import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.Category;
import dev.patika.library2.entities.Publisher;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> findAll(){
        List<BookDto> bookDtoList = this.bookService.findAll().stream().map(
book ->this.modelMapper.map(book , BookDto.class)
        ).collect(Collectors.toList());
        return bookDtoList;
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody SaveBookRequest saveBookRequest) {
        try {
            // SaveBookRequest içindeki categoryIds'i al
            List<Integer> categoryIds = saveBookRequest.getCategoryIds();

            // Book sınıfına map et
            Book book = this.modelMapper.map(saveBookRequest, Book.class);

            // Yazarı ayarla
            Author author = new Author();
            author.setId(saveBookRequest.getBook_author_id());
            book.setAuthor(author);

            // Yayıncıyı ayarla
            Publisher publisher = new Publisher();
            publisher.setId(saveBookRequest.getBook_publisher_id());
            book.setPublisher(publisher);

            // CategoryId'leri kullanarak kategorileri al ve kitaba ekle
            List<Category> categories = categoryIds.stream()
                    .map(categoryId -> {
                        Category category = new Category();
                        category.setId(categoryId);
                        return category;
                    })
                    .collect(Collectors.toList());

            book.setCategoryList(categories);

            // Kitabı kaydet
            Book savedBook = this.bookService.save(book);

            // BookDto'yu doldur
            BookDto bookDto = new BookDto();
            bookDto.setId(savedBook.getId());
            bookDto.setName(savedBook.getName());
            bookDto.setPublicationYear(savedBook.getPublicationYear());
            bookDto.setStock(savedBook.getStock());

            // Başarı mesajını servis katmanından al
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.getBookSuccessMessage(savedBook));
        } catch (Exception e) {
            // Hata durumunda buraya düşer
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookService.getBookErrorMessage());
        }
    }




    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@PathVariable("id") int bookId, @RequestBody SaveBookRequest saveBookRequest) {
        try {
            // Veritabanından güncellenecek kitabı getir
            Book existingBook = bookService.getById(bookId);

            // SaveBookRequest içindeki bilgileri mevcut kitaba map et
            modelMapper.map(saveBookRequest, existingBook);

            // Yazarı ayarla
            Author author = new Author();
            author.setId(saveBookRequest.getBook_author_id());
            existingBook.setAuthor(author);

            // Yayıncıyı ayarla
            Publisher publisher = new Publisher();
            publisher.setId(saveBookRequest.getBook_publisher_id());
            existingBook.setPublisher(publisher);

            // CategoryId'leri kullanarak kategorileri al ve kitaba ekle
            List<Category> categories = saveBookRequest.getCategoryIds().stream()
                    .map(categoryId -> {
                        Category category = new Category();
                        category.setId(categoryId);
                        return category;
                    })
                    .collect(Collectors.toList());

            existingBook.setCategoryList(categories);

            // Kitabı güncelle
            Book updatedBook = bookService.save(existingBook);

            // Güncellenmiş kitabın bilgilerini döndür
            BookDto updatedBookDto = new BookDto();
            updatedBookDto.setId(updatedBook.getId());
            updatedBookDto.setName(updatedBook.getName());
            updatedBookDto.setPublicationYear(updatedBook.getPublicationYear());
            updatedBookDto.setStock(updatedBook.getStock());

            // Başarı mesajını servis katmanından al
            return ResponseEntity.ok(bookService.getBookSuccessMessage(updatedBook));
        } catch (EntityNotFoundException e) {

            // Diğer hata durumları için
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookService.getBookErrorMessage());
        }
    }


    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            this.bookService.delete(id);
            return ResponseEntity.ok(bookService.getBookDeleteSuccessMessage(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookService.getBookDeleteErrorMessage());
        }
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable("id") int id){
        return this.bookService.getById(id);
    }



}
