package ru.mud.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mud.springcourse.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book,Integer> {
    void deleteBookById(int id);
    Book findByNameStartingWith(String search);
}
