package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ; // 100000
    public static final int ADMIN_ID = START_SEQ + 1; // 100001
    public static final int MEAL_ID = START_SEQ + 2; // Outmeal, user, 100002
    public static final int MEAL_ID2 = START_SEQ + 3; // Omelette, admin, 100003
    public static final int MEAL_ID3 = START_SEQ + 4; // Borsht, userq, 100004

    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(LocalDate.of(2020, 6, 15), LocalTime.of(19, 10, 11)), "Outmeal", 176);
    public static final Meal MEAL2 = new Meal(MEAL_ID2, LocalDateTime.of(LocalDate.of(2020, 6, 16), LocalTime.of(14, 10, 26)), "Omelette", 154);
    public static final Meal MEAL3 = new Meal(MEAL_ID3, LocalDateTime.of(LocalDate.of(2020, 6, 17), LocalTime.of(15, 10, 25)), "Borscht", 98);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "Orange", 60);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        return updated;
    }

    public static Meal getUpdatedAnotherCustomer() {
        Meal updated = new Meal(MEAL2);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
       // assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
        assertThat(actual).isEqualTo(expected);
    }
}
