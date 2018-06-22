package com.example.ng_tiofack.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ng_tiofack.moodtracker.R;
import com.example.ng_tiofack.moodtracker.model.Mood;
import com.example.ng_tiofack.moodtracker.model.SavedValue;
import com.example.ng_tiofack.moodtracker.utils.MoodResources;
import com.example.ng_tiofack.moodtracker.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // declaration of the gesture detector object
    private GestureDetectorCompat mGestureObject;

    //Image's view from activity main layout
    private ImageView mImageView;

    // default mood stored as an ID
    int mMoodID = 3;

    //comment left by the user on a mood
    String mCommentary;

    //Button to view the historic and Button to set a comment
    Button mHistButton, mCommentButton;

    //defined a save value class to get the saved value
    SavedValue mySavedValue;

    //instantiate a class mood to use it in the main activity
    Mood mMood = new Mood();

    //define a empty list of mood. where the all the moods will be stored
    List<Mood> listMood = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.activity_main_img_mood);
        mGestureObject = new GestureDetectorCompat(this, new LearnGesture());


        mHistButton = findViewById(R.id.activity_main_hist_btn);
        mHistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MoodHistActivity.class);
                startActivity(intent);
            }
        });

        mCommentButton = findViewById(R.id.activity_main_comment_btn);
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(MainActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.commentary_popup, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.enter), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                mCommentary = userInputDialogEditText.getText().toString();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });
                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });

        listMood = Utils.getArrayMoodList(
                this,
                getString(R.string.save_arrayListMood__key)
        );
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public class LearnGesture implements GestureDetector.OnGestureListener

    {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

            // with a long press on a display you should be able to trigger email sending

            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(MainActivity.this);
            View mView = layoutInflaterAndroid.inflate(R.layout.email_option, null);
            AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilderUserInput.setView(mView);

            alertDialogBuilderUserInput
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {

                            Intent intent, chooser;

                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("mailto:"));

                            String to = "";
                            intent.putExtra(Intent.EXTRA_EMAIL, to);
                            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.my_mood_of_the_day));
                            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.today_i_am) + getString(MoodResources.emailMessage[mMoodID]));

                            intent.setType("message/rfc822");

                            chooser = Intent.createChooser(intent, "Send Email");
                            startActivity(chooser);

                        }
                    })
                    .setNegativeButton(getString(R.string.no),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    dialogBox.cancel();
                                }
                            });
            AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
            alertDialogAndroid.show();

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


            if (e1.getY() < e2.getY()) {
                mMoodID--;

                if (mMoodID < 0) {
                    mMoodID = 0;
                }
                mImageView.setBackgroundResource(MoodResources.mood_color[mMoodID]);
                mImageView.setImageResource(MoodResources.mood_images[mMoodID]);
            }
            if (e1.getY() > e2.getY()) {
                mMoodID++;

                if (mMoodID > 4) {
                    mMoodID = 4;
                }
                mImageView.setBackgroundResource(MoodResources.mood_color[mMoodID]);
                mImageView.setImageResource(MoodResources.mood_images[mMoodID]);
            }

            mCommentary = "";
            return true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();


        mySavedValue = mMood.getSavedMood(MainActivity.this, getString(R.string.save_commentary__key), getString(R.string.save_mood__key),
                getString(R.string.save_dayLong__key));

        long currentDay = System.currentTimeMillis() / MoodResources.timeInMilliSecToHour;

        if (mySavedValue.getSavedDay() != currentDay) {

            listMood.add(new Mood(mySavedValue.getMoodID(), mySavedValue.getSavedDay(), mySavedValue.getCommentary()));

            if (listMood.size() > MoodResources.maxDimenOfListArray) {
                listMood.remove(0);
            }

            Utils.
                    saveArrayMoodList(
                            MainActivity.this,
                            listMood,
                            getString(R.string.save_arrayListMood__key)
                    );
        }

        mMood
                .saveMood
                        (MainActivity.this,
                                getString(R.string.save_commentary__key), getString(R.string.save_mood__key), getString(R.string.save_dayLong__key),
                                mMoodID, currentDay, mCommentary);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}