package dev.patika.library2.business.concretes;

import dev.patika.library2.business.abstracts.IAuthorService;
import dev.patika.library2.dao.AuthorRepo;
import dev.patika.library2.dto.UpdateAuthorRequest;
import dev.patika.library2.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorManager implements IAuthorService {

    @Autowired
    private AuthorRepo authorRepo;


    @Override
    public Author getById(int id) {
        return this.authorRepo.findById(id).orElseThrow();
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public void delete(int id) {
        this.authorRepo.delete(this.getById(id));
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepo.findAll();
    }

    @Override
    public String getAuthorSuccessMessage(Author author) {
        return "İşlem başarılı. Yazar ID: " + author.getId();
    }


    @Override
    public String getAuthorErrorMessage() {
        return "İşlem gerçekleşmedi , bir hata oluştu.";
    }

    @Override
    public String getAuthorDeleteSuccessMessage(int id) {
        return "Yazar başarıyla silindi. Yazar ID: " + id;
    }

    @Override
    public String getAuthorDeleteErrorMessage() {
        return "Yazar silinemedi. Lütfen tekrar deneyin.";
    }


}
