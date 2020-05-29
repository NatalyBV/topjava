package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {

  public static void main(String[] args) {
    List<UserMeal> meals = Arrays.asList(
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение",
            100),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
        new UserMeal(LocalDateTime.of(2020, Month.MAY, 29, 6, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.MAY, 29, 8, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2020, Month.MAY, 29, 20, 0), "Ужин", 410),
        new UserMeal(LocalDateTime.of(2020, Month.MAY, 29, 20, 0), "Ужин", 4810)
    );

    List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0),
        LocalTime.of(12, 0), 2000);
    mealsTo.forEach(System.out::println);
  }

  public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime,
      LocalTime endTime, int caloriesPerDay) {
    List<UserMealWithExcess> mealPerDayWithExcess = new ArrayList<>();
    HashMap<LocalDate, Integer> caloriesSumPerDay = new HashMap<>();
    for (UserMeal meal : meals) {
      LocalDate mealDate = meal.getDateTime().toLocalDate();
      caloriesSumPerDay.put(mealDate,
          caloriesSumPerDay.containsKey(mealDate) ? caloriesSumPerDay.get(mealDate) + meal
              .getCalories()
              : meal.getCalories());
    }

    for (UserMeal meal : meals) {
      if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
        mealPerDayWithExcess.add(
            new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                caloriesSumPerDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
      }

    }
    return mealPerDayWithExcess;
  }

  public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals,
      LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

    Map<LocalDate, Integer> caloriesSumPerDay = meals.stream().collect(
        Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(),
            Collectors.summingInt((UserMeal::getCalories))));

    return meals.stream().filter(userMeal -> TimeUtil
        .isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
        .map(userMeal -> new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(),
            userMeal.getCalories(),
            caloriesSumPerDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
        .collect(Collectors.toList());
  }
}
