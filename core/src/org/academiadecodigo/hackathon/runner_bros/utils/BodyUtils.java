package org.academiadecodigo.hackathon.runner_bros.utils;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Runner;

/**
 * Created by cadet on 30/10/15.
 */
public class BodyUtils {

    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }
    /*
    public static Body getRunnerBody(Body a,Body b){

        if(bodyIsRunner(a)){
            return
        }

    }
    */

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

    public static boolean bodyIsWall(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.WALL;
    }

}
