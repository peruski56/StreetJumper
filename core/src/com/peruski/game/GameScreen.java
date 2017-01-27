package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Zach on 12/6/16.
 */

public class GameScreen implements Screen {
    Texture badLogic;

    final StreetJumper game;
    Background background;
    Runner runner;
    Car car;
    Sounds sounds;
    int carJumps;
    State state = State.RUN;
    PopUpScreen popUpScreen;

    private enum State {
        PAUSE,
        RUN,
        END
    }

    public GameScreen(final StreetJumper game) {
        badLogic = new Texture(Gdx.files.internal("badlogic.jpg"));

        this.game = game;

        popUpScreen = new PopUpScreen();

        carJumps = 0;

        background = new Background();

        sounds = new Sounds();
        sounds.getBackgroudMusic().play();
        sounds.getBackgroudMusic().setLooping(true);

        runner = new Runner(sounds);

        car = new Car(sounds);
    }


    @Override
    public void render(float delta) {
        switch (state) {
            case RUN:
                update();
                break;
            case PAUSE:
                pauseScreen();
                break;
            case END:
                endScreen();
                break;
            default:
                update();
                break;
        }
    }

    public void update(){
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.font.getData().setScale(2f);

        runner.jump();

        runner.setCurrentRunner();


        float accelZ = Gdx.input.getAccelerometerZ();
        float accelX = Gdx.input.getAccelerometerX();

        game.batch.begin();
        game.batch.draw(background.getBackground(), background.getBackgroundRec1().x, background.getBackgroundRec1().y, background.getBackgroundRec1().width, background.getBackgroundRec1().height);
        game.batch.draw(background.getBackground(), background.getBackgroundRec2().x, background.getBackgroundRec2().y, background.getBackgroundRec2().width, background.getBackgroundRec2().height);
        game.batch.draw(runner.getCurrentRunner(), runner.getRunnerX(), runner.getRunnerY(), runner.getRunnerWidth(), runner.getRunnerHeight());
        for (Rectangle carRec : car.getCars()) {
            game.batch.draw(car.getCarImage(), carRec.x, carRec.y, carRec.width, carRec.height);
            if(carRec.x < runner.getRunnerX() - 121 && carRec.x > runner.getRunnerX() - 125){
                carJumps++;
            }
        }
        game.font.draw(game.batch, "Cars Jumped: " + carJumps, 500, 590);
        game.batch.draw(popUpScreen.getPauseBtn(), popUpScreen.getPauseBtnRec().x, popUpScreen.getPauseBtnRec().y, popUpScreen.getPauseBtnRec().width, popUpScreen.getPauseBtnRec().height);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.justTouched() || accelZ > 10 || accelX > 10) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            if (popUpScreen.isBtnPressed(touchPos)) {
                sounds.getBackgroudMusic().stop();
                state = State.PAUSE;
            }else {
                runner.setJumping();
            }
        }

        background.moveBackground();

        car.spawnNewCar();
        if(car.moveCar(runner, game)){
            state = State.END;
        }
    }

    public void endScreen() {
        game.camera.update();
        game.font.getData().setScale(5f);

        game.batch.begin();
        game.batch.draw(popUpScreen.getScreenBackground(), popUpScreen.getScreenBackgroundRec().x, popUpScreen.getScreenBackgroundRec().y, popUpScreen.getScreenBackgroundRec().width, popUpScreen.getScreenBackgroundRec().height);
        game.batch.draw(popUpScreen.getBtnBackground(), popUpScreen.getBackToMainBtnRec().x, popUpScreen.getBackToMainBtnRec().y, popUpScreen.getBackToMainBtnRec().width, popUpScreen.getBackToMainBtnRec().height);
        game.batch.draw(popUpScreen.getBackToMainBtn(), popUpScreen.getBackToMainBtnRec().x, popUpScreen.getBackToMainBtnRec().y, popUpScreen.getBackToMainBtnRec().width, popUpScreen.getBackToMainBtnRec().height);
        game.batch.draw(popUpScreen.getBtnBackground(), popUpScreen.getContinueBtnRec().x, popUpScreen.getContinueBtnRec().y, popUpScreen.getContinueBtnRec().width, popUpScreen.getContinueBtnRec().height);
        game.batch.draw(popUpScreen.getPlayAgain(), popUpScreen.getContinueBtnRec().x, popUpScreen.getContinueBtnRec().y, popUpScreen.getContinueBtnRec().width, popUpScreen.getContinueBtnRec().height);
        game.batch.draw(runner.getCurrentRunner(), 150, 250, runner.getRunnerWidth() * 2, runner.getRunnerHeight() * 2);
        game.batch.draw(car.getCarImage(), 900, 250, 220, 100);
        game.font.draw(game.batch, "Road Kill!!!", 400, 520);
        game.font.draw(game.batch, "HighScore: " + game.prefs.getInteger("highscore", 0), 370, 420);
        game.font.draw(game.batch, "Current Score: " + carJumps, 310, 320);
        if(game.prefs.getInteger("highscore") < carJumps){
            game.font.draw(game.batch, "New Highscore!", 320, 230);
        }
        game.batch.end();


        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            if(popUpScreen.isContinueBtnPressed(touchPos)){
                if(game.prefs.getInteger("highscore") < carJumps){
                    game.prefs.putInteger("highscore", carJumps);
                }
                game.prefs.flush();
                game.setScreen(new GameScreen(game));
            }
            if(popUpScreen.isBackToMainBtnPressed(touchPos)){
                if(game.prefs.getInteger("highscore") < carJumps){
                    game.prefs.putInteger("highscore", carJumps);
                }
                game.prefs.flush();
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    public void pauseScreen() {
        game.camera.update();
        game.font.getData().setScale(3f);

        game.batch.begin();
        game.batch.draw(popUpScreen.getScreenBackground(), popUpScreen.getScreenBackgroundRec().x, popUpScreen.getScreenBackgroundRec().y, popUpScreen.getScreenBackgroundRec().width, popUpScreen.getScreenBackgroundRec().height);
        game.batch.draw(popUpScreen.getBtnBackground(), popUpScreen.getBackToMainBtnRec().x, popUpScreen.getBackToMainBtnRec().y, popUpScreen.getBackToMainBtnRec().width, popUpScreen.getBackToMainBtnRec().height);
        game.batch.draw(popUpScreen.getBackToMainBtn(), popUpScreen.getBackToMainBtnRec().x, popUpScreen.getBackToMainBtnRec().y, popUpScreen.getBackToMainBtnRec().width, popUpScreen.getBackToMainBtnRec().height);
        game.batch.draw(popUpScreen.getBtnBackground(), popUpScreen.getContinueBtnRec().x, popUpScreen.getContinueBtnRec().y, popUpScreen.getContinueBtnRec().width, popUpScreen.getContinueBtnRec().height);
        game.batch.draw(popUpScreen.getContinueBtn(), popUpScreen.getContinueBtnRec().x, popUpScreen.getContinueBtnRec().y, popUpScreen.getContinueBtnRec().width, popUpScreen.getContinueBtnRec().height);
        game.batch.draw(popUpScreen.getWaiting(), popUpScreen.getWaitingRec().x, popUpScreen.getWaitingRec().y, popUpScreen.getWaitingRec().width, popUpScreen.getWaitingRec().height);
        game.batch.draw(runner.getCurrentRunner(), 150, 250, runner.getRunnerWidth() * 2, runner.getRunnerHeight() * 2);
        game.batch.draw(car.getCarImage(), 900, 250, 220, 100);
        game.font.draw(game.batch, "Paused", 520, 500);
        game.batch.end();

        popUpScreen.isKeyPressed();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            if(popUpScreen.isContinueBtnPressed(touchPos)){
                sounds.getBackgroudMusic().play();
                state = State.RUN;
            }
            if(popUpScreen.isBackToMainBtnPressed(touchPos)){
                sounds.getBackgroudMusic().stop();
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        runner.dispose();
        car.dispose();
        background.dispose();
        sounds.dispose();
    }

}
