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
        if(person.getBirthday()>2024||person.getBirthday()<=1900){
            errors.rejectValue("birthday","","Incorrect year of birthday");
        }
    }
    private boolean checkCreate(List<Person>list,Person person){
        return list.isEmpty();
    }
}
