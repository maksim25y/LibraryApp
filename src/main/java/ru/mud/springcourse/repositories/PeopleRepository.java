package ru.mud.springcourse.repositories;

import org.springframework.stereotype.Repository;
import ru.mud.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person>getPersonByInfo(String info);
}
