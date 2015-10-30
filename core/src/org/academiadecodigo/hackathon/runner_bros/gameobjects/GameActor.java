package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;

/**
 * Created by cadet on 30/10/15.
 */
public abstract class GameActor extends Actor {

    protected Body body;
    protected UserData userData;
    protected Sprite sprite;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();

    public Sprite getSprite() {
        return sprite;
    }
}
