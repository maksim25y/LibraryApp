package ru.mud.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mud.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class)).stream().toList();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(info,birthday) VALUES(?,?);"
        ,person.getInfo(),person.getBirthday());
    }

    public Person getById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET info=?,birthday=? WHERE id=?"
                ,person.getInfo(),person.getBirthday(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }

    public Optional<Person> getByName(String info) {
        return jdbcTemplate.query("SELECT * FROM person WHERE info=?",
                new Object[]{info},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void changeUserId(int id, int userId) {
        jdbcTemplate.update("UPDATE book SET user_id=? WHERE id=?",userId,id);
    }
}
