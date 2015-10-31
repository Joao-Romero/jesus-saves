package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by cadet on 30/10/15.
 */
public class Runner extends GameActor {

    private boolean jumping;
    private boolean needsVerticalImpulse;
    private boolean nextToRunner;

    public void setNextToRunner(boolean nextToRunner) {
        this.nextToRunner = nextToRunner;
    }


    public boolean needsVerticalImpulse() {
        return needsVerticalImpulse;
    }

    public void setNeedsVerticalImpulse(Boolean needsVerticalImpulse){
        this.needsVerticalImpulse = needsVerticalImpulse;
    }

    public boolean isNextToWall() {
        return nextToWall;
    }

    public void setNextToWall(boolean nextToWall) {
        this.nextToWall = nextToWall;
    }

    private boolean nextToWall;

    public boolean canJump(){
        if(jumping){

        }
        //System.out.println(this+" "+ jumping);
        //System.out.println(this+" "+ nextToWall);
        //System.out.println(this+" "+ nextToRunner);
        return !jumping || nextToWall || nextToRunner ? true:false;
    }


    public Runner(Body body,Sprite sprite) {

        super(body);

        //sprite = AssetManager.instance.runnerSprites.get(runnerType);
        this.sprite = sprite;
    }


    public void landed() {
        System.out.println("landed");
        jumping = false;
    }


    public float getBodyPositionX(){

        return body.getPosition().x;
    }



    public float getBodyPositionY(){

        return body.getPosition().y;
    }

    public Body getBody(){

        return super.body;
    }



    public void jump() {
        needsVerticalImpulse = true;
        if (!(jumping)) {
            jumping = true;
        }

    }




}
