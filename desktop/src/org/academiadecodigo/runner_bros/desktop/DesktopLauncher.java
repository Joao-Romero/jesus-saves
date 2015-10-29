package org.academiadecodigo.runner_bros.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.hackathon.runner_bros.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Runner Bros";
		config.width = 1024;
		config.height = 768;

		new LwjglApplication(new Main(), config);
	}
}
