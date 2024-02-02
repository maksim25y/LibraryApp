package ru.mud.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.dao.PersonDao;
import ru.mud.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;
    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String getPeople(Model model){
        model.addAttribute("people",personDao.index());
        return "people/index";
    }
    @GetMapping("/new")
    public String addGet(Model model){
        model.addAttribute("person",new Person());
        return "people/new";
    }
    @PostMapping
    public String addNew(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personDao.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("person",personDao.getById(id));
        return "people/show";
    }
    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable("id")int id,Model model){
        model.addAttribute("person",personDao.getById(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person")Person person,
                               BindingResult bindingResult,@PathVariable("id") int id){
        personDao.update(id,person);
        return "redirect:/people";
    }
}
