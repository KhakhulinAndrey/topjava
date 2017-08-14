package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by 1 on 14.08.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }
    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        MATCHER.assertEquals(MEAL_1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL4_ID, USER_ID); //чужая еда
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_2, MEAL_3), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(MEAL1_ID, ADMIN_ID); //чужая еда
    }

    @Test
    public void testGetBetweenDate() throws Exception {
        Collection<Meal> between =  service.getBetweenDates(LocalDate.of(2017, 8, 12),LocalDate.of(2017, 8, 14),USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_1, MEAL_2, MEAL_3), between);
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<Meal> between =  service.getBetweenDateTimes(LocalDateTime.parse("2017-08-13T18:00"),
                LocalDateTime.parse("2017-08-13T23:00"), USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_1, MEAL_2), between);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_1, MEAL_2, MEAL_3), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL_1);
        updated.setDescription("UpdatedEat");
        updated.setCalories(330);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Meal updated = new Meal(MEAL_1);
        updated.setDescription("UpdatedEat");
        updated.setCalories(330);
        service.update(updated, ADMIN_ID); //чужая еда
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, USER_ID, LocalDateTime.MAX, "new eat", 400);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(newMeal, MEAL_1, MEAL_2, MEAL_3), service.getAll(USER_ID));
    }

}