package org.academiadecodigo.hackathon.runner_bros.utils;

import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;


/**
 * Created by cadet on 30/10/15.
 */
public class BodyUtils {

    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }


    public static Body[] order(Body a, Body b){
        Body[] array = new Body[2];
        array[0] = bodyIsRunner(a) ? a : b;
        array[1] = bodyIsRunner(a) ? b : a;
        return array;
    }


    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

    public static boolean bodyIsWall(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.WALL;
    }

}
