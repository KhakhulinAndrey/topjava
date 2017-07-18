package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by 1 on 17.07.2017.
 */
public class MemoryMealDAOImpl implements MealDAO {

    private static final Logger log = getLogger(MemoryMealDAOImpl.class);

    private static AtomicInteger counter = new AtomicInteger();
    public static ConcurrentHashMap<Integer, Meal> db = new ConcurrentHashMap<>();
    public static List<Meal> mealsDB = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public MemoryMealDAOImpl() {
        mealsDB.forEach(m -> m.setId(counter.getAndIncrement()));
        mealsDB.forEach(m -> db.put(m.getId(), m));
    }

    @Override
    public void add(Meal meal) {
        int id = counter.getAndIncrement();
        meal.setId(id);
        db.put(id, meal);
        log.debug("meal ID=" + id + " added");
    }

    @Override
    public void delete(int mealId) {
        db.remove(mealId);
        log.debug("meal ID=" + mealId + " deleted");
    }

    @Override
    public void update(Meal meal) {
        db.put(meal.getId(), meal);
        log.debug("meal ID=" + meal.getId() + " updated");
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Meal getById(int mealId) {
        return db.get(mealId);
    }
}
