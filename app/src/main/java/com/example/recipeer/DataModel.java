package com.example.recipeer;

public class DataModel {
    // fields
    private String Recipes;
    private String Ingredients;

    // constructors
    public DataModel() {}
    public DataModel(String recipes, String ingredients) {
        this.Ingredients = ingredients;
        this.Recipes = recipes;
    }
    // properties
    public void setIngredients(String ingredients) {
        this.Ingredients = ingredients;
    }
    public String getIngredients() {
        return this.Ingredients;
    }
    public void setRecipes(String recipes) {
        this.Recipes = recipes;
    }
    public String getRecipes() {
        return this.Recipes;
    }
}
