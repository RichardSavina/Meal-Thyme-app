package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class RecipeController {

    private RecipeDao recipeDao;

    public RecipeController(RecipeDao recipeDao){this.recipeDao = recipeDao;}

    @RequestMapping(path = "/recipes", method = RequestMethod.GET)
    public List<Recipe> getAllRecipes(){
        return recipeDao.findAll();
    }

    @RequestMapping(path = "/recipe/{recipeId}", method = RequestMethod.GET)
    public Recipe getRecipeById(Long recipeId){
        return recipeDao.findRecipeById(recipeId);
    }

    @RequestMapping(path = "/recipes/{recipeName")
    public List<Recipe> getRecipesByName(String recipeName){
        return recipeDao.findRecipeByName(recipeName);
    }
}