package dev.patika.library2.business.concretes;

import dev.patika.library2.business.abstracts.IAuthorService;
import dev.patika.library2.business.abstracts.ICategoryService;
import dev.patika.library2.dao.AuthorRepo;
import dev.patika.library2.dao.BookRepo;
import dev.patika.library2.dao.CategoryRepo;
import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private BookRepo bookRepo;


    @Override
    public Category getById(int id) {
        return this.categoryRepo.findById(id).orElseThrow();
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        Category categoryToDelete = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kategori bulunamadı. ID: " + id));

        List<Book> booksInCategory = bookRepo.findBooksByCategoryId(id);
        if (!booksInCategory.isEmpty()) {
            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
        }

        categoryRepo.delete(categoryToDelete);
        return "Kategori başarıyla silindi. Kategori ID: " + id;
    }


    @Override
    public List<Category> findAll() {
        return this.categoryRepo.findAll();
    }

    @Override
    public String getSuccessMessage(Category category) {
        return "İşlem başarılı. Kategori ID: " + category.getId();
    }

    @Override
    public String getErrorMessage() {
        return "İşlem gerçekleşmedi , bir hata oluştu.";
    }


}
