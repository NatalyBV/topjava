package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository mealRepository) {
        this.repository = mealRepository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id, int userId) {
        Meal meal = checkNotFoundWithId(repository.get(id), id);
        if (meal.getUserId().equals(userId)) {
            repository.delete(id); // уже ведь нашлась в любом случае? тем более не надо checkNotFoundWithId(repository.get(id), id);
        } else {
            throw new NotFoundException("Meal doesn't belong to this user.");
        }
    }

    public Meal get(int id, int userId) {
        Meal meal = checkNotFoundWithId(repository.get(id), id);
        if (meal.getUserId().equals(userId)) {
            return meal;
        } else {
            throw new NotFoundException("Meal doesn't belong to this user.");
        }
    }

    public Collection<Meal> getAll() {
        return repository.getAll();
    }

    public void update(Meal meal, int userId) {
        if (meal.getUserId().equals(userId)) {
            checkNotFoundWithId(repository.save(meal), meal.getId());
        } else {
            throw new NotFoundException("Meal doesn't belong to this user.");
        }
    }
}