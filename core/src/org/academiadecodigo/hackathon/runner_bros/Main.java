package org.academiadecodigo.hackathon.runner_bros;

import com.badlogic.gdx.Game;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.manager.GameManager;
import org.academiadecodigo.hackathon.runner_bros.screens.CreditsScreen;
import org.academiadecodigo.hackathon.runner_bros.screens.GameOverScreen;
import org.academiadecodigo.hackathon.runner_bros.screens.GameScreen;
import org.academiadecodigo.hackathon.runner_bros.screens.MenuScreen;
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryonet.Client;

/**
 * Created by cadet on 29/10/15.
 */
public class Main extends Game{
    public static AudioManager audioManager;
    public static GameManager gameManager;
    public static MenuScreen menuScreen;
    public static GameScreen gameScreen;
    public static GameOverScreen gameOverScreen;
    public static CreditsScreen creditsScreen;

    @Override
    public void create() {
        System.out.println("test");
        gameManager = new GameManager(this);

        menuScreen = new MenuScreen();
        gameScreen = new GameScreen();
        gameOverScreen = new GameOverScreen(this);
        creditsScreen = new CreditsScreen(this);
        setScreen(menuScreen);
    }
}
