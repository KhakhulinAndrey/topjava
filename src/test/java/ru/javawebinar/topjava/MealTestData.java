package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int MEAL2_ID = START_SEQ + 3;
    public static final int MEAL3_ID = START_SEQ + 4;
    public static final int MEAL4_ID = START_SEQ + 5;
    public static final int MEAL5_ID = START_SEQ + 6;


    public static final Meal MEAL_1 = new Meal(MEAL1_ID, LocalDateTime.parse("2017-08-13T22:24"), "BigMac", 500);
    public static final Meal MEAL_2 = new Meal(MEAL2_ID, LocalDateTime.parse("2017-08-13T18:24"), "Wopper", 580);
    public static final Meal MEAL_3 = new Meal(MEAL3_ID, LocalDateTime.parse("2017-08-13T15:24"), "Vodka", 220);
    public static final Meal MEAL_4 = new Meal(MEAL4_ID, LocalDateTime.parse("2017-08-13T11:24"), "Admin eat1", 888);
    public static final Meal MEAL_5 = new Meal(MEAL5_ID, LocalDateTime.parse("2017-08-13T10:24"), "Admin eat2", 777);


/*              (100000, '2017-08-13 22:24', 'BigMac', 500),
                (100000, '2017-08-13 18:24', 'Wopper', 580),
                (100000, '2017-08-13 15:24', 'Vodka', 220),
                (100001, '2017-08-13 11:24', 'Admin eat1', 888),
                (100001, '2017-08-13 10:24', 'Admin eat2', 777);*/

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            ((expected, actual) -> expected == actual ||
                    (expected.toString().equals(actual.toString()))
            )
    );

}
