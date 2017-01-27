package com.peruski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {

    final StreetJumper game;
    Background background;
    Rectangle startGameRec;
    Rectangle aboutRec;
    PopUpScreen popUpScreen;
    State state;
    public enum State{
        MENU,
        ABOUT
    }

    public MainMenuScreen(final StreetJumper game) {
        this.game = game;
        background = new Background(0);
        startGameRec = new Rectangle();
        aboutRec = new Rectangle();
        popUpScreen = new PopUpScreen();
        state = State.MENU;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        switch (state){
            case MENU:
                menuScreen();
                break;
            case ABOUT:
                aboutScreen();
                break;
            default:
                menuScreen();
                break;
        }
    }

    public void menuScreen(){
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.font.getData().setScale(5f);

        game.batch.begin();
        game.batch.draw(background.getBackground(), background.getBackgroundRec1().x, background.getBackgroundRec1().y, background.getBackgroundRec1().width, background.getBackgroundRec1().height);
        game.font.draw(game.batch, "Welcome to Street Jumper!!! ", 150, 550);
        game.batch.draw(popUpScreen.getBtnBackground(), popUpScreen.getStartRec().x, popUpScreen.getStartRec().y, popUpScreen.getStartRec().width, popUpScreen.getStartRec().height);
        game.batch.draw(popUpScreen.getStartGame(), popUpScreen.getStartRec().x, popUpScreen.getStartRec().y, popUpScreen.getStartRec().width, popUpScreen.getStartRec().height);
        game.batch.draw(popUpScreen.getBtnBackground(), popUpScreen.getAboutRec().x, popUpScreen.getAboutRec().y, popUpScreen.getAboutRec().width, popUpScreen.getAboutRec().height);
        game.batch.draw(popUpScreen.getAbout(), popUpScreen.getAboutRec().x, popUpScreen.getAboutRec().y, popUpScreen.getAboutRec().width, popUpScreen.getAboutRec().height);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            if(popUpScreen.getStartRec().contains(touchPos.x, touchPos.y)){
                game.setScreen(new GameScreen(game));
                dispose();
            }
            if(popUpScreen.getAboutRec().contains(touchPos.x, touchPos.y)){
                state = State.ABOUT;
            }
        }
    }

    public void aboutScreen(){
        game.font.getData().setScale(3f);
        Rectangle backToMainRec = new Rectangle(400, popUpScreen.getBackToMainBtnRec().y, popUpScreen.getBackToMainBtnRec().width, popUpScreen.getBackToMainBtnRec().height);

        game.batch.begin();
        game.batch.draw(popUpScreen.getScreenBackground(), popUpScreen.getScreenBackgroundRec().x, popUpScreen.getScreenBackgroundRec().y, popUpScreen.getScreenBackgroundRec().width, popUpScreen.getScreenBackgroundRec().height);
        game.batch.draw(popUpScreen.getBtnBackground(), backToMainRec.x, backToMainRec.y, backToMainRec.width, backToMainRec.height);
        game.batch.draw(popUpScreen.getBackToMainBtn(), backToMainRec.x, backToMainRec.y, backToMainRec.width, backToMainRec.height);
        game.font.draw(game.batch, "Images:", 55, 500);
        game.font.draw(game.batch, "Sounds:", 55, 350);
        game.font.getData().setScale(1f);
        game.font.draw(game.batch, "Runner: https://www.pinterest.com/fcoutto/sprite-sources/", 300, 500);
        game.font.draw(game.batch, "Car: http://opengameart.org/content/2d-car-sprite-2", 300, 470);
        game.font.draw(game.batch, "Background: https://www.behance.net/gallery/16532917/2D-Game-Backgrounds", 300, 440);
        game.font.draw(game.batch, "Meme: http://epicrapbattlesofhistory.wikia.com/wiki/File:Well..._We're_Waiting.png", 300, 410);
        game.font.draw(game.batch, "Music: http://www.orangefreesounds.com/psychedelic-trip-electronic-music-beat-126-bpm/", 300, 350);
        game.font.draw(game.batch, "Jump: http://www.freesfx.co.uk", 300, 320);
        game.font.draw(game.batch, "Crash: http://www.freesfx.co.uk", 300, 290);
        game.batch.end();

        if(Gdx.input.justTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            if(backToMainRec.contains(touchPos.x, touchPos.y)){
                state = State.MENU;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
