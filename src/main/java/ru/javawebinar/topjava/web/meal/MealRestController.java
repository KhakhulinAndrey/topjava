package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        int userId = AuthorizedUser.id();
        return MealsUtil.getFilteredWithExceeded(service.getAll(userId), LocalTime.MIN, LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        int userId = AuthorizedUser.id();
        return service.save(meal, userId);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public void update(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("update {} with id={}", meal, userId);
        service.update(meal, userId);
    }

    public List<MealWithExceed> getFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getFiltred");
        int userId = AuthorizedUser.id();
        return MealsUtil.getFilteredWithExceeded(service.getFiltered(startDate, endDate, userId), startTime, endTime,
                AuthorizedUser.getCaloriesPerDay());
    }

}