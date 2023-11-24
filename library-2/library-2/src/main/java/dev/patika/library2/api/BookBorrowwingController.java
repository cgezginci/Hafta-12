package dev.patika.library2.api;

import dev.patika.library2.business.abstracts.IBookBorrowwingService;
import dev.patika.library2.dto.*;
import dev.patika.library2.entities.Author;
import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.BookBorrowwing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class BookBorrowwingController {

    @Autowired
    private IBookBorrowwingService bookBorrowwingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/book-borrowwings")
    @ResponseStatus(HttpStatus.OK)
    public List<BookBorrowwingDto> findAll(){
        List<BookBorrowwingDto> bookBorrowwingDtoList = this.bookBorrowwingService.findAll().stream().map(
                bookBorrowwing ->this.modelMapper.map(bookBorrowwing , BookBorrowwingDto.class)
        ).collect(Collectors.toList());
        return bookBorrowwingDtoList;
    }

    @PostMapping("/book-borrowwings")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody SaveBookBorrowwing saveBookBorrowwing) {
        try {
            BookBorrowwing bookBorrowwing = this.modelMapper.map(saveBookBorrowwing, BookBorrowwing.class);

            Book book = new Book();
            book.setId(saveBookBorrowwing.getBookborrowing_book_id());
            bookBorrowwing.setBook(book);

            this.bookBorrowwingService.save(bookBorrowwing);

            String successMessage = this.bookBorrowwingService.getBookBorrowwingSuccessMessage(bookBorrowwing);

            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception e) {

            String errorMessage = this.bookBorrowwingService.getBorrowError();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    @PutMapping("/book-borrowwings")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update (@RequestBody UpdateBookBorrowwingDto updateBookBorrowwingDto) {
        try {
            BookBorrowwing bookBorrowwing = this.bookBorrowwingService.getById(updateBookBorrowwingDto.getId());
            bookBorrowwing.setBorrowerName(updateBookBorrowwingDto.getBorrowerName());
            bookBorrowwing.setReturnDate(updateBookBorrowwingDto.getReturnDate());

            this.bookBorrowwingService.update(bookBorrowwing);

            String succesMessage = this.bookBorrowwingService.getBookBorrowwingSuccessMessage(bookBorrowwing);
            return ResponseEntity.status(HttpStatus.CREATED).body(succesMessage);
        } catch (Exception e){
            String errorMessage = this.bookBorrowwingService.getBookBorrowwingErrorMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

    }

    @DeleteMapping("/book-borrowwings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            this.bookBorrowwingService.delete(id);
            return ResponseEntity.ok(bookBorrowwingService.getBookBorrowwingDeleteSuccessMessage(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bookBorrowwingService.getBookBorrowwingDeleteErrorMessage());
        }
    }

    @GetMapping("/book-borrowwings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowwing getById(@PathVariable("id") int id){
        return this.bookBorrowwingService.getById(id);
    }
}
