package ru.mud.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mud.springcourse.models.Book;
import ru.mud.springcourse.models.Person;
import ru.mud.springcourse.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    public List<Book> findAll(){
        return booksRepository.findAll();
    }
    public Optional<Book> findById(int id){
        return booksRepository.findById(id);
    }
    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }
    @Transactional
    public void update(int id,Book book) {
        book.setId(id);
        booksRepository.save(book);
    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteBookById(id);
    }
    @Transactional
    public Person getBookPerson(int id) {
        return booksRepository.findById(id).get().getPerson();
    }
}