package com.example.ng_tiofack.moodtracker.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ng_tiofack.moodtracker.utils.MoodResources;

public class Mood {

    private long creationDay;
    private int mMoodID;
    private String comment;

    public Mood() {
        this.mMoodID = 3;
        this.creationDay = 0;
        this.comment = "";
    }

    public Mood(int moodID, long creationDay, String comment) {
        mMoodID = moodID;
        this.creationDay = creationDay;
        this.comment = comment;
    }

    public int getMoodID() {
        return mMoodID;
    }

    public String getComment() {
        return comment;
    }

    public void saveMood(Context context, String key_comment, String key_moodID, String key_day, int moodID, long savedDay, String Commentary) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key_comment, Commentary);
        editor.putInt(key_moodID, moodID);
        editor.putLong(key_day, savedDay);

        editor.commit();
    }


    public SavedValue getSavedMood(Context context, String prefKey_comment, String prefKey_moodID, String prefKey_day) {

        long current_day = System.currentTimeMillis() / MoodResources.timeInMilliSecToHour;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SavedValue mSavedValues = new SavedValue(

                sharedPreferences.getString(prefKey_comment, ""),
                sharedPreferences.getInt(prefKey_moodID, 3),
                sharedPreferences.getLong(prefKey_day, current_day)
        );

        return mSavedValues;
    }

}