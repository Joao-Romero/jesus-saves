package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.academiadecodigo.hackathon.runner_bros.WorldController;

/**
 * Created by cadet on 30/10/15.
 */
public class GameObject {
    public Sprite getSprite() {
        return sprite;
    }

    protected Sprite sprite;
    protected Rectangle collisionBox;

    public GameObject(Sprite sprite){
        this.sprite = sprite;
        WorldController.gameObjectManager.addGameObject(this);
    }

    public void draw(SpriteBatch batch) {
        System.out.println("sprite width:" +sprite.getWidth());
        System.out.println("sprite x:" +sprite.getX());
        System.out.println("sprite height:" +sprite.getHeight());
        System.out.println("sprite y:" +sprite.getY());
        sprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
    }
}
