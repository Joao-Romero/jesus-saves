package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.GroundUserData;

/**
 * Created by cadet on 30/10/15.
 */
public class Ground extends GameActor {

    public Ground(Body body) {
        super(body);
    }

    @Override
    public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }

    public Body getBody(){

        return super.body;
    }
}

