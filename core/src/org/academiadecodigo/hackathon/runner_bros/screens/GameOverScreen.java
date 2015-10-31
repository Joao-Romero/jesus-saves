package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import org.academiadecodigo.hackathon.runner_bros.Main;

/**
 * Created by cadet on 31/10/15.
 */
public class GameOverScreen implements Screen {

    private Game game;

    public GameOverScreen(Game game){
        this.game = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.R) || Gdx.input.isTouched()) {
            Main.gameScreen = new GameScreen(game);
            game.setScreen(Main.gameScreen);
            Gdx.input.setInputProcessor(Main.gameScreen.getStage());
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
