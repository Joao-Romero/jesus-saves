package org.academiadecodigo.hackathon.runner_bros.screens;

//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.manager.SOUND;
import org.academiadecodigo.hackathon.runner_bros.stage.GameStage;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by cadet on 30/10/15.
 */
public class GameScreen implements Screen {
    private GameStage stage;
    private Image bgImage;

    public GameStage getStage() {
        return stage;
    }


    @Override
    public void show(){
        AudioManager.instance.playMusic(SOUND.GAME);
        stage = new GameStage();
        Main.gameManager.startTimer();
        bgImage = AssetManager.instance.bgImage;
    }

    @Override
    public void render(float delta){

        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        bgImage.setPosition((Constants.APP_WIDTH - bgImage.getWidth()) / 2, (Constants.APP_HEIGHT - bgImage.getHeight()) / 2);
        stage.addActor(bgImage);

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
