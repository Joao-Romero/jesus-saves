package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;

/**
 * Created by cadet on 31/10/15.
 */
public class PowerUp extends GameActor {

    private UserData userData;

    public PowerUp(Body body) {
        super(body);
        userData = new UserData(UserDataType.POWERUP);
    }




}
