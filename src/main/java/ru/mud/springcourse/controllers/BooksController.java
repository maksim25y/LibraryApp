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
import ru.mud.springcourse.models.Person;

import java.util.Optional;

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
    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book",new Book());
        return "books/new";
    }
    @PostMapping
    public String createBook(@ModelAttribute("book")@Valid Book book,BindingResult bindingResult){
        bookDao.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String getBook(@PathVariable("id")int id,Model model){
        Book book = bookDao.getById(id);
        model.addAttribute("book",book);
        if(book.getUserId().isEmpty()) {
            model.addAttribute("people", personDao.index());
            model.addAttribute("give", new Person());
        }else {
            model.addAttribute("person", personDao.getById(book.getUserId().get()));
        }
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
    @PostMapping("/{id}")
    public String changeBook(@RequestParam(value = "userId",required = false) Integer userId, @PathVariable("id")int id,
                             @ModelAttribute("person") Person person){
        if(userId==null)
            personDao.changeUserId(id,person.getId());
        else
            personDao.clearBook(id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.delete(id);
        return "redirect:/books";
    }
}
