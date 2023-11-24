package dev.patika.library2.api;

import dev.patika.library2.business.abstracts.IAuthorService;
import dev.patika.library2.dto.UpdateAuthorRequest;
import dev.patika.library2.entities.Author;
import dev.patika.library2.entities.Book;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.Any;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> findAll(){
        return authorService.findAll();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody Author author) {
        try {
            Author savedAuthor = this.authorService.save(author);
            String successMessage = this.authorService.getAuthorSuccessMessage(savedAuthor);
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception e) {
            String errorMessage = this.authorService.getAuthorErrorMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@RequestBody UpdateAuthorRequest updateAuthorRequest) {
        try {
            Author updateAuthor = this.authorService.getById(updateAuthorRequest.getId());
            updateAuthor.setName(updateAuthorRequest.getName());
            updateAuthor.setBirthday(updateAuthorRequest.getBirthday());
            updateAuthor.setCountry(updateAuthorRequest.getCountry());

            this.authorService.update(updateAuthor);

            String successMessage = this.authorService.getAuthorSuccessMessage(updateAuthor);
            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
        } catch (Exception e) {
            String errorMessage = this.authorService.getAuthorErrorMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            this.authorService.delete(id);
            return ResponseEntity.ok(authorService.getAuthorDeleteSuccessMessage(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authorService.getAuthorDeleteErrorMessage());
        }
    }


    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getById(@PathVariable("id") int id){
        return this.authorService.getById(id);
    }


}
