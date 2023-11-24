package dev.patika.library2.business.abstracts;


import dev.patika.library2.entities.Author;
import dev.patika.library2.entities.Publisher;

import java.util.List;

public interface IPublisherService {

    Publisher getById (int id);

    Publisher save (Publisher publisher);

    Publisher update (Publisher publisher);

    void delete (int id);

    List<Publisher> findAll();

    String getPublisherSuccessMessage(Publisher publisher);

    String getPublisherErrorMessage();

    String getPublisherDeleteSuccessMessage(int id);

    String getPublisherDeleteErrorMessage();
}
