package ru.mud.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.mud.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setEmail(rs.getString("email"));
        person.setId(rs.getInt("id"));
        person.setAge(rs.getInt("age"));
        person.setName(rs.getString("name"));
        return person;
    }
}
