package com.peruski.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StreetJumper extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	OrthographicCamera camera;
	Preferences prefs;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 600);
		prefs = Gdx.app.getPreferences("streetJumperPreference");
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
