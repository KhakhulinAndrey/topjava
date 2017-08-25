package ru.javawebinar.topjava.service.Meal;

import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by Admin on 24.08.2017.
 */
@ActiveProfiles({Profiles.POSTGRES_DB, Profiles.JPA})
public class JpaMealServiceTest extends MealServiceTest {
}
