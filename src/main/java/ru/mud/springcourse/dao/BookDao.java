package ru.mud.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mud.springcourse.models.Book;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book",new BeanPropertyRowMapper<>(Book.class)).stream().toList();
    }

    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void updateBook(int id, Book book) {
        jdbcTemplate.update("person SET name=?,author=?,date=? WHERE id=?",
                book.getName(),book.getAuthor(),book.getDate());
    }
}
