package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal extends BaseEntity {

    private int user_id;

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    public Meal(){}

    public Meal(Meal m){
        this(m.getId(), m.getUser_id(), m.getDateTime(), m.getDescription(), m.getCalories());
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, null, dateTime, description, calories);
    }

    public Meal(Integer id, Integer user_id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.user_id = user_id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getUser_id() {
        return user_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                "user_id=" + user_id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
