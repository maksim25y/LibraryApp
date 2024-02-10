package ru.mud.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.models.Book;
import ru.mud.springcourse.models.Person;
import ru.mud.springcourse.util.PersonValidator;
import ru.mud.springcourse.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String getPeople(Model model){
        model.addAttribute("people",peopleService.findAll());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("person",peopleService.findById(id).get());
        List<Book> books = peopleService.getBooksByPersonId(id);
        if(!books.isEmpty())
            model.addAttribute("books",books);
        else
            model.addAttribute("notBooks",true);
        return "people/show";
    }

    @GetMapping("/new")
    public String addGet(Model model){
        model.addAttribute("person",new Person());
        return "people/new";
    }
    @PostMapping
    public String addNew(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())return "people/new";
        peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable("id")int id,Model model){
        model.addAttribute("person",peopleService.findById(id).get());
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,@PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())return "people/edit";
        peopleService.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        System.out.println(id);
        peopleService.delete(id);
        return "redirect:/people";
    }
}