package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Zach on 1/20/17.
 */

public class Background {

    Texture backgroundImage;
    Rectangle backgroundRec1;
    Rectangle backgroundRec2;
    Array<Rectangle> backgroundArray;

    public Background(){
        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));//https://www.behance.net/gallery/16532917/2D-Game-Backgrounds
        backgroundRec1 = new Rectangle(0, 0, 1200, 600);
        backgroundRec2 = new Rectangle(1200, 0, 1200, 600);
        backgroundArray = new Array<Rectangle>();

    }

    public void moveBackground(){
        backgroundRec1.x -= 100 * Gdx.graphics.getDeltaTime();
        backgroundRec2.x -= 100 * Gdx.graphics.getDeltaTime();
        if(backgroundRec1.x < -1190){
            backgroundRec1.x = 1190;
        }
        if(backgroundRec2.x < -1190){
            backgroundRec2.x = 1190;
        }

    }

    public Texture getBackground(){
        return backgroundImage;
    }

    public Rectangle getBackgroundRec1(){
        return backgroundRec1;
    }

    public Rectangle getBackgroundRec2(){
        return backgroundRec2;
    }

    public void dispose(){
        backgroundImage.dispose();
    }
}
