package dev.patika.library2.business.abstracts;

import dev.patika.library2.dao.AuthorRepo;
import dev.patika.library2.dto.UpdateAuthorRequest;
import dev.patika.library2.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public interface IAuthorService {


    Author getById (int id);

    Author save (Author author);

    Author update (Author author);

    void delete (int id);

    List<Author> findAll();

    String getAuthorSuccessMessage(Author author);

    String getAuthorErrorMessage();

    String getAuthorDeleteSuccessMessage(int id);

    String getAuthorDeleteErrorMessage();


}
