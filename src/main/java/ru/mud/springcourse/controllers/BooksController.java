package ru.mud.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        if(book.getUserId().isEmpty())
            model.addAttribute("people",personDao.index());
        else
            model.addAttribute("person",personDao.getById(book.getUserId().get()));
        return "books/show";
    }
    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable("id")int id,Model model){
        model.addAttribute("book",bookDao.getById(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id")int id,
                             @ModelAttribute("book")@Valid Book book,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors())return "books/edit";
        bookDao.updateBook(id,book);
        return "redirect:/books";
    }
}
