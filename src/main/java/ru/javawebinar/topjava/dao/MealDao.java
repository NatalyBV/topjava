package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class MealDao {
    private final List<Meal> mealList = Collections.synchronizedList(
            new ArrayList<>(Arrays.asList(
                    new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                    new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 15, 13, 0), "Обед", 1000),
                    new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 10, 20, 0), "Ужин", 500),
                    new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 10, 0, 0), "Еда на граничное значение", 700),
                    new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 10, 10, 0), "Завтрак", 1000),
                    new Meal(LocalDateTime.of(2020, Month.APRIL, 30, 13, 0), "Обед", 500),
                    new Meal(LocalDateTime.of(2020, Month.APRIL, 30, 20, 0), "Ужин", 410)
            )));

    public Meal get(String id) {
        for (Meal meal : mealList) {
            if (meal.getId().equals(id)) {
                return meal;
            }
        }
        return null;
    }

    public Collection<Meal> getAll() {
        return mealList;
    }

    public String save(Meal meal) {
        mealList.add(meal);
        return meal.getId();
    }

    public void update(String id, Meal meal) {
        for (int i = 0; i < mealList.size(); i++) {
            if (Objects.equals(mealList.get(i).getId(), id)) {
                mealList.set(i, meal);
                return;
            }
        }
    }

    public void delete(String id) {
        for (Meal meal : mealList) {
            if (meal.getId().equals(id)) {
                mealList.remove(meal);
                return;
            }
        }
    }
}
