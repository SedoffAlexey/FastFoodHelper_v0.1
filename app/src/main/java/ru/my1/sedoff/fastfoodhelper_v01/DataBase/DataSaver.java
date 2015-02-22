package ru.my1.sedoff.fastfoodhelper_v01.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aleksej on 21.02.15.
 */
public class DataSaver {
    Context context;
    public static final String APP_PREFERENCES = "data";//название файла настроек
    public static final String APP_PREFERENCES_MCD = "mcd";//база mcdonalds
    public static final String APP_PREFERENCES_KFC = "kfc";//база kfc
    public static final String APP_PREFERENCES_BGK = "bgk";//база burger king
    SharedPreferences mSettings;

    public DataSaver(Context c){
        context = c; //подгрузка всех ресурсов
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); //файл настроек создается, доступ только компон. приложения
    }

    public Set<String> ReadTable (int resource){
        try {
            InputStream inputStream = context.getResources().openRawResource(resource);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            Set<String> set = new HashSet<>();
            while ((line = reader.readLine()) != null)
                set.add(line);
            return set;
        } catch (IOException e) {
            Log.i("ERROR", "in reading res file");
        }
        return null;
    }

    public void RefreshFirmTable (String firmName, Set<String> set){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putStringSet(firmName, set);
        editor.apply();
    }



}
