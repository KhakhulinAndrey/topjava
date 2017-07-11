package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceededOnStreams(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceededOnStreams(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
    {
        //мапа для определения переедания калорий за каждый день
        Map<LocalDate, Integer> map = mealList.stream()
                .collect(Collectors.toMap(m -> m.getDateTime().toLocalDate(), UserMeal::getCalories, (old, newV) -> old + newV));


        List<UserMealWithExceed> result = mealList.stream()
                .filter(m -> m.getDateTime().toLocalTime().isAfter(startTime) && m.getDateTime().toLocalTime().isBefore(endTime))
                .map(meal -> {
                    boolean exceed = map.get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
                    UserMealWithExceed mealWithExceed = new UserMealWithExceed(meal.getDateTime(),
                            meal.getDescription(), meal.getCalories(), exceed);
                    return mealWithExceed;
                })
                .collect(Collectors.toList());

        return result;
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //мапа для определения переедания калорий за каждый день
        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal meal : mealList)
        {
            LocalDate mealDate = meal.getDateTime().toLocalDate();
            int cal = meal.getCalories();

            if (map.containsKey(mealDate))
                map.put(mealDate, map.get(mealDate) + cal);
            else
                map.put(mealDate, cal);
        }

        List<UserMealWithExceed> result = new ArrayList<>();
        for (UserMeal meal: mealList)
        {
            LocalDate mealDate = meal.getDateTime().toLocalDate();
            LocalTime mealTime = meal.getDateTime().toLocalTime();
            boolean exeeded = map.get(mealDate) > caloriesPerDay;

            if (mealTime.isAfter(startTime) && mealTime.isBefore(endTime))
                result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(),
                         meal.getCalories(), exeeded));

        }
        return result;
    }
}
