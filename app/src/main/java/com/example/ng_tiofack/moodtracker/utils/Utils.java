package com.example.ng_tiofack.moodtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ng_tiofack.moodtracker.model.Mood;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void saveArrayMoodList(Context context, List<Mood> list, String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }

    public static List<Mood> getArrayMoodList (Context context, String key){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<List<Mood>>() {}.getType();
        List<Mood> listMood =  gson.fromJson(json, type);
        return listMood == null ? new ArrayList<Mood>() : listMood;
    }
}
