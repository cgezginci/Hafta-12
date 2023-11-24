package dev.patika.library2.dao;

import dev.patika.library2.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher , Integer> {

}
