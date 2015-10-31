package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.stage.GameStage;

/**
 * Created by cadet on 30/10/15.
 */
public class GameScreen implements Screen {
    private GameStage stage;
    private Game game;

    public GameStage getStage() {
        return stage;
    }


    public GameScreen(Game game) {
        stage = new GameStage();
        this.game = game;
        Main.gameManager.startTimer();
    }

    @Override
    public void show(){
        Main.audioManager.playMusic(AudioManager.gameMusic);
    }

    @Override
    public void render(float delta){

        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        // empty
    }

    @Override
    public void pause() {
        // empty
    }

    @Override
    public void resume() {
        // empty
    }

    @Override
    public void hide() {
        // empty
    }

    @Override
    public void dispose() {
        // empty
    }
}
