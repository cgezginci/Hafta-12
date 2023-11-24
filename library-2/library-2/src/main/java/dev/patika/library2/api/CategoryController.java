package dev.patika.library2.api;


import dev.patika.library2.business.abstracts.ICategoryService;
import dev.patika.library2.dto.CategoryDto;
import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.Category;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryService.findAll();

        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> {
                    CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

                    // Book adlarını al
                    List<String> bookNames = category.getBookList().stream()
                            .map(Book::getName)
                            .collect(Collectors.toList());

                    // Book adlarını set et
                    categoryDto.setBookNames(bookNames);
                    return categoryDto;
                })
                .collect(Collectors.toList());

        return categoryDtos;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> save(@RequestBody Category category) {
        try {
            Category savedCategory = this.categoryService.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.getSuccessMessage(savedCategory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(categoryService.getErrorMessage());
        }
    }



    @PutMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Category update(@RequestBody Category category){
        return this.categoryService.save(category);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        try {
            String deleteResult = categoryService.delete(id);
            return ResponseEntity.ok(deleteResult);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kategori bulunamadı. ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kategori silinirken bir hata oluştu.");
        }
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getById(@PathVariable("id") int id){
        return this.categoryService.getById(id);
    }
}
