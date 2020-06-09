package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealDao {
    private List<MealTo> mealList = Arrays.asList(
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, false, 1),
            new MealTo(LocalDateTime.of(2020, Month.FEBRUARY, 15, 13, 0), "Обед", 1000, false, 2),
            new MealTo(LocalDateTime.of(2020, Month.FEBRUARY, 10, 20, 0), "Ужин", 500, true, 3),
            new MealTo(LocalDateTime.of(2020, Month.FEBRUARY, 10, 0, 0), "Еда на граничное значение", 700, true, 4),
            new MealTo(LocalDateTime.of(2020, Month.FEBRUARY, 10, 10, 0), "Завтрак", 1000, true, 5),
            new MealTo(LocalDateTime.of(2020, Month.APRIL, 31, 13, 0), "Обед", 500, false, 6),
            new MealTo(LocalDateTime.of(2020, Month.APRIL, 31, 20, 0), "Ужин", 410, false, 7)
    );

    public Optional<MealTo> get(int id) {
        return Optional.ofNullable(mealList.get(id));
    }

    public Collection<MealTo> getAll() {
        return mealList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public int save(MealTo mealTo) {
        mealList.add(mealTo);
        int index = mealList.size() - 1;
        mealTo.setId(index);
        return index;
    }

    public void update(int id) {
      //  mealList.set(
    }

    public void delete(int id) {
        mealList.set(mealList.indexOf(id), null);
    }
}
