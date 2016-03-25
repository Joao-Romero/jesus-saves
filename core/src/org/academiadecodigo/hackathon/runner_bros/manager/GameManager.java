package org.academiadecodigo.hackathon.runner_bros.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Runner;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerType;

/**
 * Created by cadet on 29/10/15.
 */
public class GameManager {

    private Runner winner;
    private Game game;
    private long startTime;
    private long gameTime;

    private RunnerType runnerType;
    private RunnerType runnerType2;

    public RunnerType getRunnerType() {
        return runnerType;
    }

    public void setRunnerType(RunnerType runnerType) {
        this.runnerType = runnerType;
    }

    public RunnerType getRunnerType2() {
        return runnerType2;
    }

    public void setRunnerType2(RunnerType runnerType2) {
        this.runnerType2 = runnerType2;
    }

    public long getGameTime() {
        return gameTime;
    }

    public Runner getWinner() {
        return winner;
    }

    public GameManager(Game game) {
        this.game = game;
    }

    public void setWinner(Runner winner) {
        endTimer();
        this.winner = winner;
        //this.gameOver = true;
        setScreen(Main.gameOverScreen);
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public void endTimer() {
        Long endTime = System.currentTimeMillis();
        gameTime = (endTime - startTime) / 1000;
    }



    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }
}
