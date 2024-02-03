package ru.mud.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.dao.PersonDao;
import ru.mud.springcourse.models.Person;
import ru.mud.springcourse.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
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
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())return "people/new";
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
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,@PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())return "people/edit";
        personDao.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDao.delete(id);
        return "redirect:/people";
    }
}
