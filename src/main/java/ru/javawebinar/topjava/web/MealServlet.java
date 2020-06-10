package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final long serialVersionUID = 1L;
    private static String INSERT = "/mealInsert.jsp";
    private static String EDIT = "/mealUpdate.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private final MealService mealService;

    public MealServlet() {
        mealService = new MealService(new MealDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            String mealId = request.getParameter("mealId");
            mealService.delete(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", mealService.getAllMealTo());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = EDIT;
            String mealId = request.getParameter("mealId");
            Meal mealTo = mealService.getMealById(mealId).orElse(null);
            request.setAttribute("user", mealTo);
        } else if (action.equalsIgnoreCase("meals")) {
            forward = LIST_MEAL;
            request.setAttribute("meals", mealService.getAllMealTo());
        } else {
            forward = INSERT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String mealId = request.getParameter("mealId");

        if (Objects.isNull(mealId)) {
            final Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("Date")),
                    request.getParameter("Description"),
                    Integer.parseInt(request.getParameter("Calories"))
            );
            mealService.save(meal);
        } else {
            final Meal meal = new Meal(
                    mealId,
                    LocalDateTime.parse(request.getParameter("Date")),
                    request.getParameter("Description"),
                    Integer.parseInt(request.getParameter("Calories"))
            );
            mealService.update(meal);
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("meals", mealService.getAllMealTo());
        view.forward(request, response);
    }
}
