package org.academiadecodigo.hackathon.runner_bros;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.GameObject;

/**
 * Created by cadet on 30/10/15.
 */
public class WorldRenderer {

    private WorldController worldController;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public WorldRenderer(WorldController worldController){
        this.worldController = worldController;
        init();
    }

    private void init(){
        camera = new OrthographicCamera(Main.screenWidth,Main.screenHeight);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        camera.update();

        batch = new SpriteBatch();
    }

    /**
     * Responsable for drawing all game objects to the screen
     */
    public void render(){
        // align the sprite batch with the camera
        batch.setProjectionMatrix(camera.combined);
        batch.begin();



        // render world objects
        for(GameObject gameObject : worldController.gameObjectManager.getGameObjectList()) {
            gameObject.draw(batch);
        }

        batch.end();
    }
}
