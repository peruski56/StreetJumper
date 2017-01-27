package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by Zach on 1/19/17.
 */

public class Car {

    Texture carImage;

    Rectangle carSpawnRec;
    Rectangle hitBox;

    Array<Rectangle> carsArray;
    Array<Rectangle> hitBoxesArray;

    int carSpawnTime = 300;

    Sounds sounds;

    public Car(Sounds sounds){
        carImage = new Texture(Gdx.files.internal("car.png"));//http://opengameart.org/content/2d-car-sprite-2
        carsArray = new Array<Rectangle>();
        hitBoxesArray = new Array<Rectangle>();
        this.sounds = sounds;
        spawnCars();
    }


    public void spawnCars() {
        carSpawnRec = new Rectangle(1200, 40, 220, 100);
        hitBox = new Rectangle(1212, 60, 120, 50);
        carsArray.add(carSpawnRec);
        hitBoxesArray.add(hitBox);
    }

    public void spawnNewCar(){
        if (carSpawnRec.x < carSpawnTime) {
            if(carSpawnTime < 700) {
                carSpawnTime += 20;
            }
            spawnCars();
        }
    }


    public boolean moveCar(Runner runner, StreetJumper game){
        Iterator<Rectangle> iter = carsArray.iterator();
        Iterator<Rectangle> hitIter = hitBoxesArray.iterator();
        while (iter.hasNext()) {
            Rectangle carRec = iter.next();
            Rectangle hitRec = hitIter.next();
            carRec.x -= 250 * Gdx.graphics.getDeltaTime();
            hitRec.x -= 250 * Gdx.graphics.getDeltaTime();
            if (carRec.x  < -288) {
                iter.remove();
                hitIter.remove();
            }
            if (hitRec.overlaps(runner.getRunnerRec())) {
                sounds.getCrashSound().play();
                sounds.getBackgroudMusic().setLooping(false);
                sounds.getBackgroudMusic().stop();
                return true;
            }
        }
        return false;
    }

    public Array<Rectangle> getCars(){
        return carsArray;
    }

    public Texture getCarImage(){
        return carImage;
    }

    public void dispose(){
        carImage.dispose();
    }

}
