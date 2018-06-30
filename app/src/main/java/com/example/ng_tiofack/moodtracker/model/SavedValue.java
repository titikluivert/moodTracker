package com.example.ng_tiofack.moodtracker.model;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class SavedValue {


    private String Commentary;
    private long savedDay;
    private int MoodID;

    public SavedValue(String commentary, int moodID, long savedDay) {
        Commentary = commentary;
        MoodID = moodID;
        this.savedDay = savedDay;
    }

    public String getCommentary() {
        return Commentary;
    }

    public long getSavedDay() {
        return savedDay;
    }

    public int getMoodID() {
        return MoodID;
    }

}
