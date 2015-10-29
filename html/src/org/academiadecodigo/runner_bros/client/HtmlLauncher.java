package org.academiadecodigo.runner_bros.client;

import org.academiadecodigo.runner_bros.Main;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(1024, 768);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Main();
        }
}