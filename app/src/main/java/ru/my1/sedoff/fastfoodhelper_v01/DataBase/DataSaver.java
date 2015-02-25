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

    public Set<String> readTable (int resource){
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

    public void saveTable (String tableName, Set<String> set){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putStringSet(tableName, set);
        editor.apply();
    }

    public FirmTable loadFirmTable (String tableName) {
        if(mSettings.contains(tableName)) {
            FirmTable firmTable = new FirmTable(tableName);
            Set<String> set = mSettings.getStringSet(tableName, new HashSet<String>());
            for (String string : set){
                String[] strArr = string.split(" ");
                String id = strArr[0];
                firmTable.name.put(id, strArr[1]);
                firmTable.price.put(id, strArr[2]);
                firmTable.calories.put(id, strArr[3]);
                Set<String> ingredients = new HashSet<>();
                for (int i = 4; i < strArr.length; i++)
                    ingredients.add(strArr[i]);
                firmTable.ingredients.put(id, ingredients);
            }

            //<<<TEST//
            for (String testMess : firmTable.ingredients.get("002"))
                Log.i("TESTER", testMess);
            Log.i("TESTER", firmTable.firmName);
            //TEST>>>//

            return firmTable;
        }
        else return null;
    }

    public IngredsTable loadIngredsTable(){
        if(mSettings.contains("ingredients"))
        {
            IngredsTable ingredsTable = new IngredsTable();
            Set<String> set = mSettings.getStringSet("ingredients", new HashSet<String>());
            for (String string : set){
                String[] strArr = string.split(" ");
                String id = strArr[0];
                ingredsTable.name.put(id, strArr[1]);
            }
            return ingredsTable;
        }
        else return null;
    }
}
