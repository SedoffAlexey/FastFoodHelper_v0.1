package ru.my1.sedoff.fastfoodhelper_v01.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 28.02.2015.
 */
public class Rating {
    String firmName;
    static final String APP_PREFERENCES_FREQ = "frequency";
    static final String APP_PREFERENCES_RATE = "rating";
    SharedPreferences mSettings;
    Map<String, Integer> frequency;//частота
    Map<String, Integer> rating;
    public Rating() {
        firmName="";
        frequency = new HashMap<>();
        rating = new HashMap<>();
    }
    public Rating(String fname) {
        firmName=fname;
        frequency = new HashMap<>();
        rating = new HashMap<>();
    }
    public void boostFrequency(String key) {
        if (frequency.containsKey(key)) {
            frequency.put(key, frequency.get(key) + 1);
        }
        else
        {
            frequency.put(key, 1);
        }
    }
    public void setRating(String key,Integer Value) {
        rating.put(key,Value);
    }
    public int getFreq(String key) {
        return frequency.get(key);
    }
    public int getRate(String key) {
        return rating.get(key);
    }
    protected void UpdatePref(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> pair : map.entrySet())
        {
            String key = pair.getKey();    //ключ
            Integer value = pair.getValue();      //значение
            UpdatePrefValue(key,value);
        }
    }
    protected void UpdatePrefValue(String key, Integer value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public void load(Context c, final String s) {
        mSettings = c.getSharedPreferences(firmName + s, Context.MODE_PRIVATE);
        Map <String,?> buf=mSettings.getAll();
   //     for(Map.Entry<String,?> entry : buf.entrySet()){
           // Log.d("map values",entry.getKey() + ": " +
       //             entry.getValue().toString());
       // }
        for (Map.Entry<String, ?> pair : buf.entrySet())
        {
            String key = pair.getKey();    //ключ
            Integer value = Integer.parseInt(pair.getValue().toString());      //значение
            switch(s)
            {
                case APP_PREFERENCES_FREQ:
                    frequency.put(key,value);
                case APP_PREFERENCES_RATE:
                    rating.put(key,value);
            }
        }
    }
    public void save(Context c, final String s) {
        mSettings = c.getSharedPreferences(String.format("%s%s", firmName, s), Context.MODE_PRIVATE);
        switch(s)
            {
                case APP_PREFERENCES_FREQ:
                    UpdatePref(frequency);
                case APP_PREFERENCES_RATE:
                    UpdatePref(rating);
            }
    }
    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

        // Convert Map to List
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

