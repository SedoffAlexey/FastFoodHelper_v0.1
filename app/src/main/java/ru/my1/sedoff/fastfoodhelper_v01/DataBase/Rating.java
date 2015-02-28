package ru.my1.sedoff.fastfoodhelper_v01.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
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
        frequency.put(key,frequency.get(key)+1);
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
        mSettings = c.getSharedPreferences(firmName+s, Context.MODE_PRIVATE);
        switch(s)
            {
                case APP_PREFERENCES_FREQ:
                    UpdatePref(frequency);
                case APP_PREFERENCES_RATE:
                    UpdatePref(rating);
            }
    }

}

