package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MealService {

    private final MealDao mealDao;

    public MealService(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    public Optional<Meal> getMealById(String id) {
        return Optional.ofNullable(mealDao.get(id));
    }

    public void save(Meal meal) {
        mealDao.save(meal);
    }

    public void update(Meal meal) {
        mealDao.update(meal.getId(), meal);
    }

    public Collection<MealTo> getAllMealTo() {
        final Collection<Meal> meals = mealDao.getAll();
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), LocalTime.of(7, 0), LocalTime.of(12, 0)))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > 2_000))
                .collect(Collectors.toList());
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public void delete(String id) {
        mealDao.delete(id);
    }
}
