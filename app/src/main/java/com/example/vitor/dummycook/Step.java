package com.example.vitor.dummycook;

import java.io.Serializable;

public class Step implements Serializable{

    private String textStep;
    private String titleStep;
    private int index;
    private String imageStep;
    private int videoStep;
    private String timerStep;


    public Step(String textStep, String titleStep, int index, String imageStep, int videoStep, String timerStep) {
        this.textStep = textStep;
        this.titleStep = titleStep;
        this.index = index;
        this.imageStep = imageStep;
        this.videoStep = videoStep;
        this.index = index;
        this.timerStep = timerStep;
    }

    public String getTextStep() {
        return textStep;
    }

    public void setTextStep(String textStep) {
        this.textStep = textStep;
    }

    public String getTitleStep() {
        return titleStep;
    }

    public void setTitleStep(String titleStep) {
        this.titleStep = titleStep;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageStep() {
        return imageStep;
    }

    public void setImageStep(String imageStep) {
        this.imageStep = imageStep;
    }

    public int getVideoStep() {
        return videoStep;
    }

    public void setVideoStep(int videoStep) {
        this.videoStep = videoStep;
    }

    public String getTimerStep() {
        return timerStep;
    }

    public void setTimerStep(String timerStep) {
        this.timerStep = timerStep;
    }

}
