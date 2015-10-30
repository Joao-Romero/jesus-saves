package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.WorldController;

/**
 * Created by cadet on 30/10/15.
 */
public class GameScreen extends AbstractScreen {

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show(){
        Main.gameManager.setScreen(this);

    }

    @Override
    public void render(float delta){
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



    }
}
