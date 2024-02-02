package ru.mud.springcourse.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Person {
    private int id;
    @NotEmpty(message = "Your name cannot be empty")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+",
            message = "Your personal info should be in this format: Surname Name Patronymic")
    private String info;
    @Min(value = 1901,message = "Your age cannot be more than 120 years old")
    @Max(value = 2024,message = "Your year of birthday cannot be more than 2024")
    private int birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public Person() {
    }

    public Person(int id, String info, int birthday) {
        this.id = id;
        this.info = info;
        this.birthday = birthday;
    }
}
