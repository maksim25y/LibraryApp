package ru.mud.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mud.springcourse.models.Book;

public interface BooksRepository extends JpaRepository<Book,Integer> {
    void deleteBookById(int id);
}
