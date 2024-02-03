package ru.mud.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

@Component
public class Book {
    private int id;
    @Pattern(regexp = "[а-яёА-ЯЁ]+")
    private String name;
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ ")
    private String author;
    @Min(value = 0,message = "Year cannot be less than 0")
    @Max(value = 2024,message = "Your year of birthday cannot be more than 2024")
    private int date;
    private int userId;
    public Book(){

    }

    public Book(int id, String name, String author, int date, int userId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
