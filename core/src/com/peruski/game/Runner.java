package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Zach on 1/19/17.
 */

public class Runner {
    Texture runnerImage;
    Texture currentRunner;
    Texture firstRunnerImage;
    Texture secondRunnerImage;
    Texture jumpRunnerImage;

    Rectangle runner;

    Sounds sounds;

    boolean jumping = false;

    final int GROUND = 60;

    int run = 0;
    int switchRun = 0;
    int playJump = 0;

    final float GRAVITY = 200f;
    float runnerYVel = 0.0f;

    public Runner(Sounds sounds){
        firstRunnerImage = new Texture(Gdx.files.internal("firstRun.png"));//https://www.pinterest.com/fcoutto/sprite-sources/
        secondRunnerImage = new Texture(Gdx.files.internal("secondRun.png"));//https://www.pinterest.com/fcoutto/sprite-sources/
        jumpRunnerImage = new Texture(Gdx.files.internal("jumpRun.png"));//https://www.pinterest.com/fcoutto/sprite-sources/
        currentRunner = firstRunnerImage;

        this.sounds = sounds;

        runner = new Rectangle();
        runner.x = 50;
        runner.y = GROUND;
        runner.width = 64;
        runner.height = 64;
    }

    public void setCurrentRunner(){
        run++;
        if(run == 10){
            run = 0;
            if(switchRun == 0){
                currentRunner = secondRunnerImage;
                switchRun++;
            }else{
                currentRunner = firstRunnerImage;
                switchRun--;
            }
        }
        if(runner.y == GROUND){
            runnerImage = currentRunner;
        }
        if(jumping){
            currentRunner = jumpRunnerImage;
        }
    }

    public float getRunnerX(){
        return runner.x;
    }

    public float getRunnerY(){
        return runner.y;
    }

    public float getRunnerWidth(){
        return runner.width;
    }

    public float getRunnerHeight(){
        return runner.height;
    }

    public void jump(){
        if(jumping) {
            if (playJump == 0) {
                playJump++;
                sounds.getJumpingSound().play();
            }
            runner.y += runnerYVel * Gdx.graphics.getDeltaTime();
            runnerYVel -= GRAVITY * Gdx.graphics.getDeltaTime();

            if (runner.y < GROUND) {
                runner.y = GROUND;
                playJump--;
                jumping = false;
            }
        }
    }

    public void setJumping(){
        if(!jumping) {
            jumping = true;
            runnerYVel = 200;
        }
    }

    public Rectangle getRunnerRec(){
        return runner;
    }

    public Texture getCurrentRunner(){
        return currentRunner;
    }

    public void dispose(){
        runnerImage.dispose();
        currentRunner.dispose();
        firstRunnerImage.dispose();
        secondRunnerImage.dispose();
        jumpRunnerImage.dispose();
    }


}
