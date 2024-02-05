package ru.mud.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Book {
    private int id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "[а-яА-Я\\s]+",message = "Название книги должно быть на русском языке")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+",
            message = "Информация об авторе должна быть в следующем формате: Фамилия Имя Отчество и на русском языке")
    private String author;
    @Min(value = 0,message = "Год выпуска не может быть меньше 0")
    @Max(value = 2024,message = "Год выпуска не может быть больше 2024")
    private int date;
    private Optional<Integer> userId;
    public Book(){

    }

    public Book(int id, String name, String author, int date, Optional<Integer>userId) {
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

    public Optional<Integer> getUserId() {
        return userId;
    }

    public void setUserId(Optional<Integer> userId) {
        this.userId = userId;
    }
}
