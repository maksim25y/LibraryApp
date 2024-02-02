package ru.mud.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mud.springcourse.models.Person;

import java.util.List;

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
}
