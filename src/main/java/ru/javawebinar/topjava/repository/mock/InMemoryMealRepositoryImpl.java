package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.DateTimeUtil.*;



@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> repository.put(counter.getAndIncrement(), m));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setId(userId);
        } else if (meal.getUserId() != userId)
            return null;

        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = get(id, userId);
        if (meal == null)
            return false;
        else {
            repository.remove(id);
            return true;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal.getUserId() == userId)
            return meal;
        else return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.values().stream()
                .filter(meal -> isBetween(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }
}

