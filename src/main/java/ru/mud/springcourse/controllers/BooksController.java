package ru.mud.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mud.springcourse.dao.BookDao;

@Controller
@Component
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;
    @Autowired
    public BooksController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("books",bookDao.index());
        return "books/index";
    }
}
