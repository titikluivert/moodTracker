package com.example.ng_tiofack.moodtracker.utils;

import com.example.ng_tiofack.moodtracker.R;


/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class MoodResources {
    public static int mood_images[] = {R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.drawable.ic_sentiment_dissatisfied_black_24dp,
            R.drawable.ic_sentiment_neutral_black_24dp, R.drawable.ic_sentiment_satisfied_black_24dp,
            R.drawable.ic_sentiment_very_satisfied_black_24dp};

    public static int mood_color[] = {R.color.colorVeryDissatisfied, R.color.colorDissatisfied, R.color.colorNeutral,
            R.color.colorSatisfied, R.color.colorVerySatisfied};

    public static final int maxDimenOfListArray = 7;

    public static int widthOfRelLyt[] = {1, 2, 3, 4, 5};

    public static int textViewMsg[] = {R.string.yesterday, R.string.before_yesterday,
            R.string.three_days_ago, R.string.four_days_ago, R.string.five_days_ago,
            R.string.six_days_ago, R.string.a_week_ago};

    public static int emailMessage[] = {R.string.very_bad_mood, R.string.bad_mood,
            R.string.neutral_mood, R.string.good_mood, R.string.very_good_mood};

    public static final long timeInMilliSecToHour = 86400000;
}
