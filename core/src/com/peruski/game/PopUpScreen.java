package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Zach on 1/22/17.
 */

public class PopUpScreen {

    Rectangle screenBackgroundRec;
    Rectangle continueBtnRec;
    Rectangle backToMainBtnRec;
    Rectangle pauseBtnRec;
    Rectangle waitingRec;
    Rectangle startRec;
    Rectangle aboutRec;

    private Texture screenBackground;
    private Texture pauseBtn;
    private Texture continueBtn;
    private Texture backToMainBtn;
    private Texture btnBackground;
    private Texture waiting;
    private Texture startGame;
    private Texture about;
    private Texture playAgain;


    public PopUpScreen(){

        screenBackgroundRec = new Rectangle(50, 30, 1100, 500);
        continueBtnRec = new Rectangle(650, 50, 400, 100);
        backToMainBtnRec = new Rectangle(150, 50, 400, 100);
        pauseBtnRec = new Rectangle(1130, 520, 60, 60);
        waitingRec = new Rectangle(420, 190, 350, 250);
        startRec = new Rectangle(450, 300, 300, 100);
        aboutRec = new Rectangle(450, 100, 300, 100);


        screenBackground = new Texture(Gdx.files.internal("screenBackground.png"));
        pauseBtn = new Texture(Gdx.files.internal("pauseBtn.png"));
        continueBtn = new Texture(Gdx.files.internal("continue.png"));
        backToMainBtn = new Texture(Gdx.files.internal("backToMainBtn.png"));
        btnBackground = new Texture(Gdx.files.internal("btnBackground.png"));
        waiting = new Texture(Gdx.files.internal("waiting.png"));//http://epicrapbattlesofhistory.wikia.com/wiki/File:Well..._We're_Waiting.png
        startGame = new Texture(Gdx.files.internal("startGame.png"));
        about = new Texture(Gdx.files.internal("about.png"));
        playAgain = new Texture(Gdx.files.internal("playAgain.png"));
    }

    public Texture getPlayAgain() {
        return playAgain;
    }

    public Rectangle getAboutRec() {
        return aboutRec;
    }

    public Texture getAbout() {
        return about;
    }

    public Rectangle getStartRec() {
        return startRec;
    }

    public Texture getStartGame() {
        return startGame;
    }

    public Texture getWaiting(){
        return waiting;
    }

    public Rectangle getWaitingRec() {
        return waitingRec;
    }

    public Texture getBackToMainBtn() {
        return backToMainBtn;
    }

    public Texture getBtnBackground() {
        return btnBackground;
    }

    public Texture getScreenBackground() {
        return screenBackground;
    }

    public Texture getContinueBtn() {
        return continueBtn;
    }

    public Texture getPauseBtn() {
        return pauseBtn;
    }

    public boolean isKeyPressed(){
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            return true;
        }
        return false;
    }

    public boolean isBtnPressed(Vector3 touchPos){
        if (pauseBtnRec.contains(touchPos.x, touchPos.y)) {
            return true;
        }
        return false;
    }

    public boolean isContinueBtnPressed(Vector3 touchPos){
        if (continueBtnRec.contains(touchPos.x, touchPos.y)) {
            return true;
        }
        return false;
    }

    public boolean isBackToMainBtnPressed(Vector3 touchPos){
        if (backToMainBtnRec.contains(touchPos.x, touchPos.y)) {
            return true;
        }
        return false;
    }

    public Rectangle getPauseBtnRec(){
        return pauseBtnRec;
    }

    public Rectangle getScreenBackgroundRec(){
        return screenBackgroundRec;
    }

    public Rectangle getContinueBtnRec(){
        return continueBtnRec;
    }

    public Rectangle getBackToMainBtnRec(){
        return backToMainBtnRec;
    }

}
