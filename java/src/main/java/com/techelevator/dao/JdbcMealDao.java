package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class JdbcMealDao implements MealDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMealDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Meal findMealDetailsById(Long mealId) {
        Meal meal = null;
        String sql = "SELECT * FROM meals WHERE meal_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealId);
        if (results.next()) {
            meal = mapRowToMeal(results);
        }
        return meal;
    }

    @Override
    public List<Meal> findMealsByDay(String mealDay) {
        List <Meal> mealList = new ArrayList<>();
        String sql = "SELECT * FROM meals WHERE day_of_week = ?;";
        SqlRowSet mealDetails = jdbcTemplate.queryForRowSet(sql, mealDay);
        while (mealDetails.next()){
            Meal meal = mapRowToMeal(mealDetails);
            mealList.add(meal);
        }
        return mealList;
    }

    @Override
    public List<Meal> findMealsByMealType(String mealType) {
        List <Meal> meals = new ArrayList<>();
        String sql = "SELECT * FROM meals WHERE type_of_meal = ?;";
        SqlRowSet mealResults = jdbcTemplate.queryForRowSet(sql, mealType);
        while (mealResults.next()){
            Meal meal =mapRowToMeal(mealResults);
            meals.add(meal);
        }
        return meals;
    }

    private Meal mapRowToMeal(SqlRowSet mealSet) {
        Meal meal = new Meal();
        meal.setMealId(mealSet.getLong("meal_id"));
        meal.setMealType(mealSet.getString("type_of_meal"));
        meal.setMealDay(mealSet.getString("day_of_week"));

        return meal;
    }

}
