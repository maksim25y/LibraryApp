package ru.mud.springcourse.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Person {
    private int id;
    private String info;
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
