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

    String getName(String id){
        return this.name.get(id);
    }
    String getPrice(String id){
        return this.price.get(id);
    }
    String getCalories(String id){
        return this.calories.get(id);
    }
    Set<String> getIngredients(String id){
        return this.ingredients.get(id);
    }

}
