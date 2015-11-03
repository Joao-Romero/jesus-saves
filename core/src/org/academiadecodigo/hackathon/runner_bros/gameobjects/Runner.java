package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import net.dermetfan.gdx.graphics.g2d.Box2DSprite;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;

/**
 * Created by cadet on 30/10/15.
 */
public class Runner extends GameActor {

    private boolean jumping;
    private boolean needsVerticalImpulse;
    private boolean nextToRunner;
    private boolean nextToWall;


    private Animation animation;
    private Sprite sprite;

    private RunnerType type;

    public Runner(Body body,RunnerType runnerType) {
        super(body);
        this.type = runnerType;
        this.sprite = new Sprite(AssetManager.instance.regions.get(runnerType));
        this.sprite.setSize(1,1);
        this.sprite.setPosition(getBody().getPosition().x + 1.6f, getBody().getPosition().y + 1.6f);
        this.animation = AssetManager.instance.animations.get(runnerType);

    }

    public Animation getAnimation() {
        return animation;
    }

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

    public RunnerType getType() {
        return type;
    }

    public boolean canJump(){
        if(jumping){

        }
        //System.out.println(this+" "+ jumping);
        //System.out.println(this+" "+ nextToWall);
        //System.out.println(this+" "+ nextToRunner);
        return !jumping || nextToWall || nextToRunner ? true:false;
    }


    public Sprite getSpriteFrame(float stateTime,boolean looping){
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, looping);
        sprite.setRegion(currentFrame);
        sprite.setPosition(getBody().getPosition().x + 1.6f, getBody().getPosition().y + 1.6f);
        return sprite;
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
