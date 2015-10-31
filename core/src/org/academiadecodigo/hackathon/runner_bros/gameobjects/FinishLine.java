package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;

/**
 * Created by cadet on 31/10/15.
 */
public class FinishLine extends GameActor{
    public FinishLine(Body body) {
        super(body);
        userData = new UserData(UserDataType.FINISHLINE);
    }
}
