package com.example.vitor.dummycook;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    private ImageButton playTimerButton;
    private ImageButton pauseTimerButton;
    private ImageButton resetTimerButton;
    private Button previousButton;
    private Button nextButton;
    private ArrayList<Step> stepListRecipe;
    private int index = 0;
    private long timeInitial = 0;
    private long timeRemaining = 0;
    private boolean isPaused = false;
    private boolean isCanceled = false;
    private static final String FORMAT = "%02d:%02d:%02d";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_step);
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
        previousButton = (Button) findViewById(R.id.button_previous);
        nextButton = (Button) findViewById(R.id.button_next);

        //progressBar.setProgressTintList(ColorStateList.valueOf(Color.Blue));


        pauseTimerButton.setEnabled(false);
        resetTimerButton.setEnabled(false);

        stepListRecipe = recipe.getStepList();

        textTimer.setVisibility(View.INVISIBLE);
        playTimerButton.setVisibility(View.INVISIBLE);
        pauseTimerButton.setVisibility(View.INVISIBLE);
        resetTimerButton.setVisibility(View.INVISIBLE);

        checkTimer();


        updateUI();

        videoStep.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoStep.isPlaying()) {
                    videoStep.pause();
                } else {
                    videoStep.start();
                }
                return  false;
            }
        });

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                index++;
                checkTimer();
                updateUI();
            }});

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    index--;
                    checkTimer();
                    updateUI();
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
        currentStep.setText("Step "+stepListRecipe.get(index).getIndex()+ " of " + stepListRecipe.size());
        progressBar.setProgress(((index+1)*100)/stepListRecipe.size());
        titleStep.setText(stepListRecipe.get(index).getTitleStep());
        textStep.setText(stepListRecipe.get(index).getTextStep());

        if(index == 0){
            previousButton.setVisibility(View.INVISIBLE);
        }else{
            previousButton.setVisibility(View.VISIBLE);
        }

        if(index == stepListRecipe.size()-1){
            nextButton.setVisibility(View.INVISIBLE);
        }else{
            nextButton.setVisibility(View.VISIBLE);
        }

        // Se nao tiver nada
        if(stepListRecipe.get(index).getImageStep().equals("") && stepListRecipe.get(index).getVideoStep() == 0){
            imageStep.setVisibility(View.INVISIBLE);
            videoStep.setVisibility(View.INVISIBLE);

        }
        // Se for video
        else if(stepListRecipe.get(index).getVideoStep() != 0 && stepListRecipe.get(index).getImageStep().equals("")){
            imageStep.setVisibility(View.INVISIBLE);
            videoStep.setVisibility(View.VISIBLE);

            String path = "android.resource://"+getPackageName()+"/"+stepListRecipe.get(index).getVideoStep();
            videoStep.setVideoURI(Uri.parse(path));
            videoStep.setSoundEffectsEnabled(true);
            AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }
        // Se for imagem
        else{
            videoStep.setVideoURI(null);
            imageStep.setVisibility(View.VISIBLE);
            videoStep.setVisibility(View.INVISIBLE);

            int resID = getResources().getIdentifier(stepListRecipe.get(index).getImageStep(), "drawable", "com.example.vitor.dummycook");
            imageStep.setImageResource(resID);
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

    public void checkTimer(){
        if(stepListRecipe.get(index).getTimerStep().equals("")){

            textTimer.setVisibility(View.INVISIBLE);
            playTimerButton.setVisibility(View.INVISIBLE);
            pauseTimerButton.setVisibility(View.INVISIBLE);
            resetTimerButton.setVisibility(View.INVISIBLE);

        }else{
            timeInitial = Integer.parseInt(stepListRecipe.get(index).getTimerStep()) * 1000;
            timeRemaining = timeInitial;

            textTimer.setVisibility(View.VISIBLE);
            playTimerButton.setVisibility(View.VISIBLE);
            pauseTimerButton.setVisibility(View.VISIBLE);
            resetTimerButton.setVisibility(View.VISIBLE);
            setInitialTime();
           }
    }


}

