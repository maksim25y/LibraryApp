package ru.mud.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mud.springcourse.dao.PersonDao;
import ru.mud.springcourse.models.Person;

import java.util.List;

@Component
public class PersonValidator implements Validator {
    private final PersonDao personDao;
    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (!personDao.getByName(person.getInfo()).isEmpty()) {
            if (personDao.getByName(person.getInfo()).get().getId() != person.getId()) {
                errors.rejectValue("info", "", "Person with this personal info already exist");
            }
        }
    }
}
