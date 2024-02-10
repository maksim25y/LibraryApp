package ru.mud.springcourse.services;

import org.springframework.transaction.annotation.Transactional;
import ru.mud.springcourse.models.Book;
import ru.mud.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mud.springcourse.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Optional<Person> findById(Integer id){return peopleRepository.findById(id);}
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id,Person person){
        person.setId(id);
        peopleRepository.save(person);
    }
    @Transactional
    public List<Book>getBooksByPersonId(int id){
        Optional<Person>person = peopleRepository.findById(id);
        return person.get().getBookList();
    }
    @Transactional
    public void deleteBookFromPerson(Book book) {
        book.getPerson().getBookList().remove(book);
        book.setPerson(null);
        System.out.println("Good");
    }

    @Transactional
    public void addBookToPeople(int id, Book book) {
        Person person = peopleRepository.findById(id).get();
        person.getBookList().add(book);
        book.setPerson(person);
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
    public Optional<Person>getPersonByInfo(String name){
        return peopleRepository.getPersonByInfo(name);
    }
}