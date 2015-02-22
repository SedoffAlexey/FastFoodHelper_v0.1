package ru.my1.sedoff.fastfoodhelper_v01.DataBase;

import java.util.HashMap;
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


}
