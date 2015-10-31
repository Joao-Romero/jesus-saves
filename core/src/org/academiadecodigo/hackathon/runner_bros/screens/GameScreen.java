package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import org.academiadecodigo.hackathon.runner_bros.Main;
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
    }

    @Override
    public void show(){
    }

    @Override
    public void render(float delta){
        /*
        // set the world controller as input processor
        if(!(Gdx.input.getInputProcessor() instanceof WorldController)){
            Gdx.input.setInputProcessor(Main.worldController);
        }

        Main.worldController.update(Gdx.graphics.getDeltaTime());

        // Sets the clear screen color to: Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        System.out.println("Drawing black screen");

        // render game world to screen
        Main.worldRenderer.render();
        */
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.act(delta);

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
