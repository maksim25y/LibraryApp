package ru.mud.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mud.springcourse.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person>index() {
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?;",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String email){
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?;",new Object[]{email},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name,age,email,address) VALUES (?,?,?,?);"
                ,person.getName(),person.getAge(),person.getEmail(),person.getAddress());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?,age=?,email=?,address=? WHERE id=?"
                ,person.getName(),person.getAge(),person.getEmail(),person.getAddress(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?",id);
    }
    ////////////////////////////

    public void testMultipleUpdate() {
        List<Person>list = create1000People();
        long before = System.currentTimeMillis();
        for(Person person:list){
            jdbcTemplate.update("INSERT INTO person(name,age,email,address) VALUES (?,?,?,?);"
                    ,person.getName(),person.getAge(),person.getEmail(),person.getAddress());
        }
        long after = System.currentTimeMillis();
        System.out.println("Time "+(after-before));
    }
    public void testBatchUpdate() {
        List<Person>list = create1000People();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO person(name,age,email,address) VALUES (?,?,?,?)"
                , new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        //Значения
                        ps.setString(1,list.get(i).getName());
                        ps.setInt(2,list.get(i).getAge());
                        ps.setString(3,list.get(i).getEmail());
                        ps.setString(4,list.get(i).getAddress());
                    }

                    @Override
                    public int getBatchSize() {
                        //Размер
                        return list.size();
                    }
                });

        long after = System.currentTimeMillis();
        System.out.println("Time "+(after-before));
    }

    private List<Person> create1000People() {
        List<Person>people = new ArrayList<>();
        for(int i=0;i<1000;i++){
            people.add(new Person(i,"Name"+i,23,"mail"+i+"@mail.ru","Russia,Krasnodar,345612"));
        }
        return people;
    }

}
