package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by cadet on 29/10/15.
 */
public class MenuScreen implements Screen {

    private Stage stage;
    private Skin skin;

    private Game game;

    public MenuScreen(Game game) {
        super();
        this.game = game;
    }

    @Override
    public void show(){
        stage = new Stage();
        skin = AssetManager.instance.skin;
        Image logo = AssetManager.instance.menuImage;

        logo.setPosition((Constants.APP_WIDTH - logo.getWidth()) / 2, (Constants.APP_HEIGHT - logo.getHeight()) / 2);
        stage.addActor(logo);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            Main.gameManager.setScreen(Main.gameScreen);
            Gdx.input.setInputProcessor(Main.gameScreen.getStage());
        }

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
