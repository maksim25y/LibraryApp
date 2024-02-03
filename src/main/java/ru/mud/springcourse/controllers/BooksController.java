package ru.mud.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mud.springcourse.dao.BookDao;
import ru.mud.springcourse.dao.PersonDao;
import ru.mud.springcourse.models.Book;

@Controller
@Component
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;
    private final PersonDao personDao;
    @Autowired
    public BooksController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("books",bookDao.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String getBook(@PathVariable("id")int id,Model model){
        Book book = bookDao.getById(id);
        model.addAttribute("book",book);
        if(book.getUserId()==0)
            model.addAttribute("people",personDao.index());
        else
            model.addAttribute("person",personDao.getById(book.getUserId()));
        return "books/show";
    }
}
