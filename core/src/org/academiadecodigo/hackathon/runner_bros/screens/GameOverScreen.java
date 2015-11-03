package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by cadet on 31/10/15.
 */
public class GameOverScreen implements Screen {

    private Game game;
    private Image image;

    public GameOverScreen(Game game){
        this.game = game;
    }
    @Override
    public void show() {
        Main.audioManager.playMusic(AudioManager.gameOverMusic);
        switch (Main.gameManager.getWinner().getType()){
            case sonic:
                image = AssetManager.instance.sonicWinner;
                break;
            case crash:
                image = AssetManager.instance.crashWinner;
                break;
            case mario:
                image = AssetManager.instance.marioWinner;
                break;
            case pikachu:
                image = AssetManager.instance.pikachuWinner;
                break;
            default:
                break;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.R) || Gdx.input.isTouched()) {
            Main.gameScreen = new GameScreen();
            game.setScreen(Main.gameScreen);
            Gdx.input.setInputProcessor(Main.gameScreen.getStage());
        }
        Stage stage = new Stage();
        Skin skin = AssetManager.instance.skin;

        image.setPosition((Constants.APP_WIDTH - image.getWidth()) / 2, (Constants.APP_HEIGHT - image.getHeight()) / 2);
        stage.addActor(image);

        Label label = new Label((int)Main.gameManager.getGameTime()+" seconds",skin);
        label.setPosition(50,(Constants.APP_HEIGHT-label.getHeight())/1.3f);
        stage.addActor(label);

        stage.draw();


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
