package ru.javawebinar.topjava.service.Meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by Admin on 24.08.2017.
 */
@ActiveProfiles({Profiles.POSTGRES_DB, Profiles.DATAJPA})
public class DataJpaMealServiceTest  extends MealServiceTest {
}
