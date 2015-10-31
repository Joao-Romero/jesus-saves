package org.academiadecodigo.hackathon.runner_bros.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Runner;

/**
 * Created by cadet on 29/10/15.
 */
public class GameManager {
    private Runner winner;
    private Boolean gameOver;
    private Game game;

    public GameManager(Game game){
        this.game = game;
    }
    public void setWinner(Runner winner) {
            this.winner = winner;
            this.gameOver = true;
            setScreen(Main.gameOverScreen);

    }

    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }
}
