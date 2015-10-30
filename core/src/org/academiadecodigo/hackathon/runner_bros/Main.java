package org.academiadecodigo.hackathon.runner_bros;

import com.badlogic.gdx.Game;
import org.academiadecodigo.hackathon.runner_bros.manager.GameManager;
import org.academiadecodigo.hackathon.runner_bros.screens.GameScreen;
import org.academiadecodigo.hackathon.runner_bros.screens.MenuScreen;

/**
 * Created by cadet on 29/10/15.
 */
public class Main extends Game{

    public static GameManager gameManager;

    public static MenuScreen menuScreen;
    public static GameScreen gameScreen;



    @Override
    public void create() {
        gameManager = new GameManager();
        //worldController = new WorldController();
        //worldRenderer = new WorldRenderer(worldController);

        //screenWidth = Gdx.graphics.getWidth();
        //screenHeight = Gdx.graphics.getHeight();

//        gameWidth = screenWidth * 2;
  //      gameHeight = screenHeight;

        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen();
        setScreen(menuScreen);

    }
}
