package com.peruski.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.peruski.game.StreetJumper;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(1200, 600);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new StreetJumper();
        }
}