package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.Main;

/**
 * Created by cadet on 29/10/15.
 */
public class MenuScreen extends AbstractScreen{

    private Stage stage;
    private Skin skin;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show(){
        stage = new Stage();
        skin = AssetManager.instance.skin;
        Image logo = AssetManager.instance.menuImage;

        logo.setPosition((Main.screenWidth - logo.getWidth()) / 2, (Main.screenHeight - logo.getHeight()) / 2);
        stage.addActor(logo);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1f, 1f, 1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            game.setScreen(Main.gameScreen);
        }

        stage.draw();
    }
}
