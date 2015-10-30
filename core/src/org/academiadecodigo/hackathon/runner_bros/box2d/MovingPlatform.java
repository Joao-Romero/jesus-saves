package org.academiadecodigo.hackathon.runner_bros.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.GameActor;

/**
 * Created by cadet on 30/10/15.
 */
public class MovingPlatform extends GameActor {

    public MovingPlatform(Body body) {
        super(body);
    }

    @Override
    public UserData getUserData() {
        return null;
    }
}
