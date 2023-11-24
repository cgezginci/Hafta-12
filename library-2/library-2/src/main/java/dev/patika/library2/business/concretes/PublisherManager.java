package dev.patika.library2.business.concretes;

import dev.patika.library2.business.abstracts.IPublisherService;
import dev.patika.library2.dao.PublisherRepo;
import dev.patika.library2.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherManager implements IPublisherService {

    @Autowired
    private PublisherRepo publisherRepo;

    @Override
    public Publisher getById(int id) {
        return this.publisherRepo.findById(id).orElseThrow();
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public void delete(int id) {
        this.publisherRepo.delete(this.getById(id));
    }

    @Override
    public List<Publisher> findAll() {
        return this.publisherRepo.findAll();
    }

    @Override
    public String getPublisherSuccessMessage(Publisher publisher) {
        return "İşlem başarılı. Yayınevi ID: " + publisher.getId();
    }

    @Override
    public String getPublisherErrorMessage() {
        return "İşlem gerçekleşmedi , bir hata oluştu.";
    }

    @Override
    public String getPublisherDeleteSuccessMessage(int id) {
        return "Yayınevi başarıyla silindi. Yayınevi ID: " + id;
    }

    @Override
    public String getPublisherDeleteErrorMessage() {
        return "Yayınevi silinemedi. Lütfen tekrar deneyin.";
    }
}
