package ru.my1.sedoff.fastfoodhelper_v01.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by aleksej on 22.02.15.
 */
public class FirmTable {
    String firmName;
    Map<String, String> name;
    Map<String, String> price;
    Map<String, String> calories;
    Map<String, Set<String>> ingredients;

    FirmTable(String s){
        firmName = s;
        name = new HashMap<>();
        price = new HashMap<>();
        calories = new HashMap<>();
        ingredients = new HashMap<>();
    }

    void putName(String id, String name){
        this.name.put(id, name);
    }
    void putPrice(String id, String price){
        this.price.put(id, price);
    }
    void putCalories(String id, String calories){
        this.calories.put(id, calories);
    }
    void putIngredients(String id, Set ingredients){
        this.ingredients.put(id, ingredients);
    }

}
