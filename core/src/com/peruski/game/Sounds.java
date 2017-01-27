package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Zach on 1/19/17.
 */

public class Sounds {

    Sound jumpingSound;
    Sound crashSound;
    Music backgroudMusic;

    public Sounds(){
        jumpingSound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));//http://www.freesfx.co.uk
        crashSound = Gdx.audio.newSound(Gdx.files.internal("crash.mp3"));//http://www.freesfx.co.uk
        backgroudMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));//http://www.orangefreesounds.com/psychedelic-trip-electronic-music-beat-126-bpm/
    }

    public Music getBackgroudMusic(){
        return backgroudMusic;
    }

    public Sound getJumpingSound(){
        return jumpingSound;
    }

    public Sound getCrashSound(){
        return crashSound;
    }

    public void dispose(){
        jumpingSound.dispose();
        crashSound.dispose();
        backgroudMusic.dispose();
    }
}
