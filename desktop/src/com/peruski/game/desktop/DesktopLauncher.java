package com.peruski.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.peruski.game.StreetJumper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Street Jumper";
		config.width = 1200;
		config.height = 600;
		new LwjglApplication(new StreetJumper(), config);
	}
}
