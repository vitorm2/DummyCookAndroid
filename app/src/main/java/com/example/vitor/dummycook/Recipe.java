package com.example.vitor.dummycook;


import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable{

    private String name;
    private String recipeImg;
    private ArrayList<Step> stepList;
    private String textRequirements;
    private String textIngredients;
    private String time;
    private String serves;
    private String cost;


    public Recipe(String name, String recipeImg, ArrayList<Step> stepList, String textRequirements, String textIngredients,
                  String time, String serves, String cost) {
        this.name = name;
        this.recipeImg = recipeImg;
        this.stepList = stepList;
        this.textRequirements = textRequirements;
        this.textIngredients = textIngredients;
        this.time = time;
        this.serves = serves;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
    }

    public String getTextRequirements() {
        return textRequirements;
    }

    public void setTextRequirements(String textRequirements) {
        this.textRequirements = textRequirements;
    }

    public String getTextIngredients() {
        return textIngredients;
    }

    public void setTextIngredients(String textIngredients) {
        this.textIngredients = textIngredients;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServes() {
        return serves;
    }

    public void setServes(String serves) {
        this.serves = serves;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
