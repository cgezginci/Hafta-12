package dev.patika.library2.business.abstracts;

import dev.patika.library2.entities.Book;
import dev.patika.library2.entities.Category;

import java.util.List;

public interface ICategoryService {

    Category getById (int id);

    Category save (Category category);

    Category update (Category category);

    String delete(int id);

    List<Category> findAll();

    String getSuccessMessage(Category category);

    String getErrorMessage();


}
