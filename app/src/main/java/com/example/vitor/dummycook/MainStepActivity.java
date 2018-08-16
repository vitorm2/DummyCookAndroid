package com.example.vitor.dummycook;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import java.util.Calendar;
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
    private int index;
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
        index = getIntent().getIntExtra("index", 0); // Recebe o valor do index


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

        pauseTimerButton.setEnabled(false);
        resetTimerButton.setEnabled(false);

        stepListRecipe = recipe.getStepList();

        // Esconde Timer
        textTimer.setVisibility(View.INVISIBLE);
        playTimerButton.setVisibility(View.INVISIBLE);
        pauseTimerButton.setVisibility(View.INVISIBLE);
        resetTimerButton.setVisibility(View.INVISIBLE);

        checkTimer();


        updateUI();

        // Metodo responsavel por iniciar e pausar o video clicando sobre ele.
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

        // Inicia o timer
        playTimerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                starResumeTimer();
        }});

        // Pausa o timer
        pauseTimerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                isPaused = true;

                resetTimerButton.setEnabled(true);
                playTimerButton.setEnabled(true);
            }
        });

        // Reinicia o timer
        resetTimerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isPaused = true;
                playTimerButton.setEnabled(true);
                timeRemaining = timeInitial;
                setInitialTime();
            }
        });

        // Atualiza a view com o conteudo do proximo passo
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pausa o timer quando umda de tela
                isPaused = true;
                playTimerButton.setEnabled(true);

                index++;
                checkTimer();
                updateUI();
            }
        });

        // Atualiza a view com o conteudo do passo anterior
        previousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Pausa o timer quando umda de tela
                isPaused = true;
                playTimerButton.setEnabled(true);

                index--;
                checkTimer();
                updateUI();
            }
        });
    }

    // Seta o valor do timer informado pelo step
    private void setInitialTime() {
        textTimer.setText(""+String.format(FORMAT,
                TimeUnit.MILLISECONDS.toHours(timeInitial),
                TimeUnit.MILLISECONDS.toMinutes(timeInitial) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(timeInitial)),
                TimeUnit.MILLISECONDS.toSeconds(timeInitial) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(timeInitial))));
    }

    // Atualiza as informações da view de acordo com o passo
    private void updateUI() {

        // Atualiza o numero do passo de acordo com o passo atual.
        currentStep.setText("Step "+stepListRecipe.get(index).getIndex()+ " of " + stepListRecipe.size());
        // Ajusta a progress bar de acordo com o passo atual.
        progressBar.setProgress(((index+1)*100)/stepListRecipe.size());

        titleStep.setText(stepListRecipe.get(index).getTitleStep());
        textStep.setText(stepListRecipe.get(index).getTextStep());

        // Retira o botao passo anterior no primeiro passo da lista
        if(index == 0){
            previousButton.setVisibility(View.INVISIBLE);
        }else{
            previousButton.setVisibility(View.VISIBLE);
        }

        // Retira o botao próximo passo no ultimo passo da lista
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

            // Tira o som do video
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

    // Inicia ou reinicia o timer
    private void starResumeTimer(){
        isPaused = false;
        isCanceled = false;

        //Disable the start and pause button
        playTimerButton.setEnabled(false);

        //Enabled the pause and cancel button
        pauseTimerButton.setEnabled(true);
        resetTimerButton.setEnabled(true);

        CountDownTimer timer;
        long millisInFuture = timeRemaining; // Tempo de incio do timer
        long countDownInterval = 1000; //1 second


        //Initialize a new CountDownTimer instance
        timer = new CountDownTimer(millisInFuture,countDownInterval){
            public void onTick(long millisUntilFinished){
                if(isPaused || isCanceled)
                {
                    cancel();
                }
                else {
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
                textTimer.setText("Done");


                playTimerButton.setEnabled(false);
                pauseTimerButton.setEnabled(false);
                resetTimerButton.setEnabled(true);
            }
        }.start();
    }

    // Mostra o timer sehouver necessidade e pega o valor do timer e atribui a uma variavel
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

