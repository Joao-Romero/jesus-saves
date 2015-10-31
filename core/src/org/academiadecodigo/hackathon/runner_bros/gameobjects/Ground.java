package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by cadet on 30/10/15.
 */
public class Ground extends GameActor {

    public Ground(Body body) {
        super(body);
    }

    public Body getBody(){

        return super.body;
    }
}

