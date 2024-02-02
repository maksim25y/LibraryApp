package ru.mud.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.dao.PersonDao;
import ru.mud.springcourse.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private PersonDao personDao;
    @Autowired
    public AdminController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String makeAdminPage(Model model, @ModelAttribute("person")Person person){
        model.addAttribute("people",personDao.index());
        return "adminPage";
    }
    @PatchMapping("/add")
    public String addAdmin(@ModelAttribute("person")Person person){
        System.out.println(person.getId());
        return "redirect:/people";
    }
}

