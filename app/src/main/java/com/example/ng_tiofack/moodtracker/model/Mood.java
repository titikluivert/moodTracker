package com.example.ng_tiofack.moodtracker.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ng_tiofack.moodtracker.R;
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

    public void saveMood(Context context, int moodID, long savedDay, String Commentary) {


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.save_commentary__key), Commentary);
        editor.putInt(context.getString(R.string.save_mood__key), moodID);
        editor.putLong(context.getString(R.string.save_dayLong__key), savedDay);

        editor.apply();
    }


    public SavedValue getSavedMood(Context context) {

        long current_day = System.currentTimeMillis() / MoodResources.timeInMilliSecToHour;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new SavedValue(

                sharedPreferences.getString(context.getString(R.string.save_commentary__key), ""),
                sharedPreferences.getInt(context.getString(R.string.save_mood__key), 3),
                sharedPreferences.getLong(context.getString(R.string.save_dayLong__key), current_day)
        );

    }

}