package org.academiadecodigo.hackathon.runner_bros;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Player;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.GameObjectManager;

/**
 * Created by cadet on 30/10/15.
 * Creates game objects on init
 *
 */
public class WorldController implements InputProcessor {

    public static GameObjectManager gameObjectManager;
    public Player sonic;

    public WorldController(){
        init();
    }

    private void init(){
        gameObjectManager = new GameObjectManager();
        Player player = new Player(new Sprite(AssetManager.instance.sonic));
        player.setPosition(100, 100);


    }

    public void update(float deltaTime){
        gameObjectManager.update(deltaTime);
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("key down");
        return true;
    }



    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
