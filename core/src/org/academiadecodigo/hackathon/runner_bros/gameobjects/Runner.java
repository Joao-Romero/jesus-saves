package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.academiadecodigo.hackathon.runner_bros.box2d.RunnerUserData;

/**
 * Created by cadet on 30/10/15.
 */
public class Runner extends GameActor {

    private boolean runningRight;
    private boolean runningLeft;

    public boolean isJumping() {
        return jumping;
    }

    private boolean jumping;
    private boolean dodging;


    public Runner(Body body,Sprite sprite) {

        super(body);

        //sprite = AssetManager.instance.runnerSprites.get(runnerType);
        this.sprite = sprite;
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }







    public void landed() {
        System.out.println("landed");
        jumping = false;
    }

    public void dodge() {
        if (!jumping) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public boolean isDodging() {
        return dodging;
    }

    public float getBodyPositionX(){

        return body.getPosition().x;
    }



    public float getBodyPositionY(){

        return body.getPosition().y;
    }

    public boolean isRunningLeft() {
        return runningLeft;
    }

    public boolean isRunningRight() {
        return runningRight;
    }

    public Body getBody(){

        return super.body;
    }



    public void jump() {

        if (!(jumping || dodging)) {
            //body.applyLinearImpulse(new Vector2(0, 20f), body.getWorldCenter(), true);
            jumping = true;
        }

    }

    public void setLeftMove(boolean t)
    {
        if(runningRight && t) runningRight = false;
        runningLeft = t;
    }
    public void setRightMove(boolean t)
    {
        if(runningLeft && t) runningLeft = false;
        runningRight = t;
    }


}
