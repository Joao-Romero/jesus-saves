package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.WallUserData;

/**
 * Created by cadet on 30/10/15.
 */
public class Wall extends GameActor {

    public Wall(Body body) {
        super(body);
    }

    @Override
    public WallUserData getUserData() {
        return (WallUserData) userData;
    }


}
