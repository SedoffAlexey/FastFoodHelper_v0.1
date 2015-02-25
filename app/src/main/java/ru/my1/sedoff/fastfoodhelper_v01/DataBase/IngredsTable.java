package ru.my1.sedoff.fastfoodhelper_v01.DataBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aleksej on 25.02.15.
 */
public class IngredsTable {
    Map<String, String> name;
    IngredsTable(){
        name = new HashMap<>();
    }
    String getName (String id){
        return this.name.get(id);
    }
}
