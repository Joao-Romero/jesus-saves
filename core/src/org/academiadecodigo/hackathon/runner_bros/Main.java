package org.academiadecodigo.hackathon.runner_bros;

import com.badlogic.gdx.Game;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.manager.GameManager;
import org.academiadecodigo.hackathon.runner_bros.screens.GameOverScreen;
import org.academiadecodigo.hackathon.runner_bros.screens.GameScreen;
import org.academiadecodigo.hackathon.runner_bros.screens.MenuScreen;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

/**
 * Created by cadet on 29/10/15.
 */
public class Main extends Game{
    public static AudioManager audioManager;
    public static GameManager gameManager;
    public static MenuScreen menuScreen;
    public static GameScreen gameScreen;
    public static GameOverScreen gameOverScreen;



    @Override
    public void create() {
        audioManager = new AudioManager();
        Main.audioManager.init();

        gameManager = new GameManager(this);
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        setScreen(menuScreen);
    }
}
