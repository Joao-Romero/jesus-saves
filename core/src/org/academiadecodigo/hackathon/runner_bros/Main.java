package org.academiadecodigo.hackathon.runner_bros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.academiadecodigo.hackathon.runner_bros.manager.GameManager;

/**
 * Created by cadet on 29/10/15.
 */
public class Main extends Game{

    public static GameManager gameManager;


    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int SCREEN_MARGIN = 10;

    @Override
    public void create() {
        gameManager = new GameManager();
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        GAME_WIDTH = SCREEN_WIDTH * 2;
        GAME_HEIGHT = SCREEN_HEIGHT;

    }
}
