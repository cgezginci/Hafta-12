package dev.patika.library2.dao;

import dev.patika.library2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface AuthorRepo extends JpaRepository<Author , Integer> {
}
