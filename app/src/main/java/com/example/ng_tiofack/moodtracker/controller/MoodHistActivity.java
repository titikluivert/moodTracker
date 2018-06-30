package com.example.ng_tiofack.moodtracker.controller;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ng_tiofack.moodtracker.R;
import com.example.ng_tiofack.moodtracker.model.Mood;
import com.example.ng_tiofack.moodtracker.utils.MoodResources;
import com.example.ng_tiofack.moodtracker.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MoodHistActivity extends AppCompatActivity {

    //define a empty list of mood. where the all the moods will be stored
    private List<Mood> savedListMood = new ArrayList<>();

    // define a index of mood use
    private int indexID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_hist);

        TextView[] textView = {findViewById(R.id.textView_0),
                findViewById(R.id.textView_1), findViewById(R.id.textView_2), findViewById(R.id.textView_3),
                findViewById(R.id.textView_4), findViewById(R.id.textView_5), findViewById(R.id.textView_6)};

        ImageView[] imageView = {findViewById(R.id.imageView_0),
                findViewById(R.id.imageView_1), findViewById(R.id.imageView_2), findViewById(R.id.imageView_3),
                findViewById(R.id.imageView_4), findViewById(R.id.imageView_5), findViewById(R.id.imageView_6)};

        RelativeLayout[] relativeLyt = {findViewById(R.id.relativeLyt_0)
                , findViewById(R.id.relativeLyt_1), findViewById(R.id.relativeLyt_2), findViewById(R.id.relativeLyt_3),
                findViewById(R.id.relativeLyt_4), findViewById(R.id.relativeLyt_5), findViewById(R.id.relativeLyt_6)};

        DisplayMetrics display = this.getResources().getDisplayMetrics();
        int width = display.widthPixels;

        for (RelativeLayout iRelLyt : relativeLyt) {
            iRelLyt.setVisibility(View.INVISIBLE);
        }

        savedListMood = Utils.
                getArrayMoodList(
                        MoodHistActivity.this,
                        getString(R.string.save_arrayListMood__key)
                );

        if (savedListMood != null) {

            for (Mood mood : savedListMood) {

                if (indexID < MoodResources.textViewMsg.length) {
                    relativeLyt[indexID].setVisibility(View.VISIBLE);
                    relativeLyt[indexID].setBackgroundResource(MoodResources.mood_color[mood.getMoodID()]);

                    double width_index = width * MoodResources.widthOfRelLyt[mood.getMoodID()] * 0.2;
                    ViewGroup.LayoutParams relLytParams = relativeLyt[indexID].getLayoutParams();
                    relLytParams.width = (int) width_index;

                    if (mood.getComment().isEmpty()) {
                        imageView[indexID].setVisibility(View.INVISIBLE);
                    }

                    int pos = (savedListMood.size() - 1) - indexID;
                    if (pos < MoodResources.textViewMsg.length) {
                        textView[indexID].setText(getString(MoodResources.textViewMsg[pos]));
                    }
                    indexID++;
                }
            }
            for (int i = 0; i < relativeLyt.length; i++) {
                final int finalI = i;
                imageView[i].
                        setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!savedListMood.get(finalI).getComment().isEmpty()) {

                                    LayoutInflater inflater = getLayoutInflater();
                                    View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.ToastCustom));

                                    TextView text = toastLayout.findViewById(R.id.textToast);
                                    text.setText(savedListMood.get(finalI).getComment());


                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setDuration(Toast.LENGTH_LONG);
                                    toast.setView(toastLayout);
                                    toast.show();

                                }
                            }
                        });

            }


        }

    }


}