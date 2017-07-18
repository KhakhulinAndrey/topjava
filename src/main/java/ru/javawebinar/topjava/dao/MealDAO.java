package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by 1 on 17.07.2017.
 */
public interface MealDAO {

    void add(Meal meal);

    void delete(int mealId);

    void update(Meal meal);

    List<Meal> getAll();

    Meal getById(int mealId);
}
