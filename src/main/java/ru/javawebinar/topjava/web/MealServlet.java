package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MemoryMealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.allMealsWithExceed;


/**
 * Created by 1 on 14.07.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDAO dao;
    private static String INSERT_OR_EDIT = "meal.jsp";
    private static String LIST_MEAL = "meals.jsp";

    public MealServlet(){
        super();
        dao = new MemoryMealDAOImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward="";
        String action = req.getParameter("action");

        if (action == null) {
            req.setAttribute("meals", allMealsWithExceed(dao.getAll()));
            //resp.sendRedirect("meals.jsp");

            log.debug("redirect to meals");
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
        else if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.delete(mealId);
            resp.sendRedirect("meals");
        }
        else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            Meal meal = dao.getById(mealId);
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("/meal.jsp").forward(req,resp);
        }
        else if (action.equalsIgnoreCase("insert")){
            req.getRequestDispatcher("/meal.jsp").forward(req, resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String id = request.getParameter("id");

        Meal meal = new Meal(dateTime, description, calories);
        if (id == null || id.isEmpty()){
            dao.add(meal);
        }
        else {
            meal.setId(Integer.parseInt(id));
            dao.update(meal);
        }

        response.sendRedirect("meals");

    }
}
