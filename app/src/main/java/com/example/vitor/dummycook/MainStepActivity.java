package com.example.vitor.dummycook;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;



import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainStepActivity extends AppCompatActivity {

    private TextView currentStep;
    private ProgressBar progressBar;
    private TextView titleStep;
    private TextView textStep;
    private ImageView imageStep;
    private VideoView videoStep;
    private TextView textTimer;
    private CountDownTimer timer2;
    private ImageButton playTimerButton;
    private ImageButton pauseTimerButton;
    private ImageButton resetTimerButton;
    private ArrayList<Step> stepListRecipe;
    private int index = 0;
    private long timeInitial = 70000;
    private long timeRemaining = 0;
    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;
    private static final String FORMAT = "%02d:%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_step);
        timeRemaining = timeInitial;
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipeSelected");

        currentStep =  (TextView) findViewById(R.id.current_step);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        titleStep = (TextView) findViewById(R.id.title_step_main);
        textStep = (TextView) findViewById(R.id.text_step_main);
        imageStep = (ImageView) findViewById(R.id.image_step_main);
        videoStep = (VideoView) findViewById(R.id.video_step_main);
        textTimer = (TextView) findViewById(R.id.text_timer);
        playTimerButton = (ImageButton) findViewById(R.id.button_play_timer);
        pauseTimerButton = (ImageButton) findViewById(R.id.button_pause_timer);
        resetTimerButton= (ImageButton) findViewById(R.id.button_reset_timer);



        pauseTimerButton.setEnabled(false);
        resetTimerButton.setEnabled(false);

        stepListRecipe = recipe.getStepList();


        setInitialTime();

        updateUI();


        playTimerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                starResumeTimer();
        }});


        pauseTimerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //When user request to pause the CountDownTimer
                isPaused = true;

                //Enable the resume and cancel button
                resetTimerButton.setEnabled(true);
                //Disable the start and pause button
                playTimerButton.setEnabled(true);
            }
        });


        resetTimerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isPaused = true;
                playTimerButton.setEnabled(true);
                timeRemaining = timeInitial;
               setInitialTime();
            }
        });

    }

    private void setInitialTime() {
        textTimer.setText(""+String.format(FORMAT,
                TimeUnit.MILLISECONDS.toHours(timeInitial),
                TimeUnit.MILLISECONDS.toMinutes(timeInitial) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(timeInitial)),
                TimeUnit.MILLISECONDS.toSeconds(timeInitial) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(timeInitial))));
    }

    private void updateUI() {
        currentStep.setText("Step "+stepListRecipe.get(index).getIndex()+ "of " + stepListRecipe.size());

        titleStep.setText(stepListRecipe.get(index).getTitleStep());
        textStep.setText(stepListRecipe.get(index).getTextStep());
        if(stepListRecipe.get(index).getImageStep() != "" && stepListRecipe.get(index).getVideoStep() == ""){
            imageStep.setVisibility(View.VISIBLE);
            int resID = getResources().getIdentifier(stepListRecipe.get(index).getImageStep(), "drawable", "com.example.vitor.dummycook");
            imageStep.setImageResource(resID);
            videoStep.setVisibility(View.INVISIBLE);
        }else if(stepListRecipe.get(index).getVideoStep() != "" && stepListRecipe.get(index).getImageStep() == ""){
            videoStep.setVisibility(View.VISIBLE);
            //int resID = getResources().getIdentifier(stepListRecipe.get(index).getVideoStep(), "drawable", "com.example.vitor.dummycook");
        }
    }


    private void starResumeTimer(){
        isPaused = false;
        isCanceled = false;

        //Disable the start and pause button
        playTimerButton.setEnabled(false);

        //Enabled the pause and cancel button
        pauseTimerButton.setEnabled(true);
        resetTimerButton.setEnabled(true);

        CountDownTimer timer;
        long millisInFuture = timeRemaining; //15 seconds
        long countDownInterval = 1000; //1 second


        //Initialize a new CountDownTimer instance
        timer = new CountDownTimer(millisInFuture,countDownInterval){
            public void onTick(long millisUntilFinished){
                //do something in every tick
                if(isPaused || isCanceled)
                {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel();
                }
                else {
                    //Display the remaining seconds to app interface
                    //1 second = 1000 milliseconds
//                    float hr = (millisUntilFinished/1000)/3600;
//                    float min = (millisUntilFinished/100)/60;
//                    float sec = millisUntilFinished/1000;
//                    textTimer.setText(hr+":"+min+":"+sec);
                    //Put count down timer remaining time in a variable
                    textTimer.setText(""+String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    timeRemaining = millisUntilFinished;
                }
            }
            public void onFinish(){
                //Do something when count down finished
                textTimer.setText("Done");

                //Enable the start button
                playTimerButton.setEnabled(false);
                //Disable the pause, resume and cancel button
                pauseTimerButton.setEnabled(false);
                resetTimerButton.setEnabled(true);
            }
        }.start();
    }
}

