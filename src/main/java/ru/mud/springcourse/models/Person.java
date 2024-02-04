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
    @NotEmpty(message = "Поле не может быть пустым")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+",
            message = "Информация о вас должна быть в следующем формате: Фамилия Имя Отчество")
    private String info;
    @Min(value = 1901,message = "Дата рождения не может быть меньше 1900")
    @Max(value = 2024,message = "Дата вашего рождения не может быть больше 2024")
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
