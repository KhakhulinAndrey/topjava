package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model){
        model.addAttribute("meals",
                MealsUtil.getWithExceeded(mealService.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/delete={id}", method = RequestMethod.GET)
    public String deleteMeal(@PathVariable("id") int id, Model model)
    {
        mealService.delete(id, AuthorizedUser.id());
        return meals(model);
    }

    @RequestMapping(value = "/update={id}", method = RequestMethod.GET)
    public String updateDeleteMeal(@PathVariable("id") int id, Model model)
    {
        final Meal meal =  (id == -1) ?
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                mealService.get(id, AuthorizedUser.id());
        model.addAttribute("meal", meal);
        return "mealForm ";
    }
}
