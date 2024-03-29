package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.manager.SOUND;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

import java.util.HashMap;

/**
 * Created by cadet on 30/10/15.
 */
public class Runner extends GameActor {

    private boolean jumping;
    private boolean needsVerticalImpulse;
    private boolean nextToRunner;
    private boolean nextToWall;

    /*
    private Texture redBar;
    private Texture greenBar;
    */

    private final static int ATTACKS_TILL_FINAL = 2;
    private int attactSinceFinal;

    private float life = 100f;

    public float barWidth(){
        return life * 340 / 100;
    }

    private RunnerState runnerState;

    /*
    public void setGreenBar(Texture greenBar) {
        this.greenBar = greenBar;
    }

    public void setRedBar(Texture redBar) {
        this.redBar = redBar;
    }
    */

    private HashMap<RunnerState,Animation> animation;
    private Sprite sprite;

    private RunnerType type;

    public Runner(World world,RunnerType runnerType) {
        super();
        float x = 10f;
        float y = 2f;
        float width = 3.5f;
        float height = 3.5f;

        runnerState = RunnerState.STOPPED;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.7f, 1.5f, new Vector2(3.1f, 3.5f), 0);
        Body body = world.createBody(bodyDef);

        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        Fixture fixture =  body.createFixture(shape, Constants.RUNNER_DENSITY);
        fixture.setFriction(1.f);
        body.setFixedRotation(true);
        fixture.setFriction(5f);

        body.resetMassData();
        body.setUserData(new UserData(UserDataType.RUNNER));
        shape.dispose();

        body.setBullet(true);
        setBody(body);

        this.type = runnerType;
        this.sprite = new Sprite(AssetManager.instance.regions.get(runnerType));
        this.sprite.setSize(width,height);
        this.sprite.setPosition(body.getPosition().x + 1.6f, body.getPosition().y + 1.6f);
        this.animation = AssetManager.instance.animations.get(runnerType);

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
        //System.out.println(this+" "+ jumping);
        //System.out.println(this+" "+ nextToWall);
        //System.out.println(this+" "+ nextToRunner);
        return !jumping || nextToWall || nextToRunner ? true:false;
    }

    public RunnerState getRunnerState() {
        return runnerState;
    }

    public void changeLifeBy(RunnerState opponentState, float damage){

        if(opponentState.equals(RunnerState.FINAL)){
            damage *= 3;
            System.out.println("change damage: " + damage);
        }

        if (runnerState.equals(RunnerState.DEFENSE)){
            damage /= 2;
        }

        life = Math.max(life - damage,0);
        //greenBar =
    }

    public void inflictDamageTo(Runner runner, float damage){
        attactSinceFinal++;
        System.out.println("inflic damage: " + damage);
        runner.changeLifeBy(getRunnerState(),damage);
    }

    public float getLife() {
        return life;
    }

    public boolean isDead(){
        return life == 0;
    }

    public void changeBox(float value){
        Shape shape = body.getFixtureList().get(0).getShape();
        shape.setRadius(value);
    }


    public Sprite getSpriteFrame(float stateTime,boolean looping){

        if(animation == null || animation.get(runnerState) == null){
            System.out.println("test");
        }

        TextureRegion currentFrame = animation.get(runnerState).getKeyFrame(stateTime, looping);

        /*
        if(currentFrame == null){
            currentFrame = animation.get(runnerState).getKeyFrames()[0];
        }
        */
        sprite.setRegion(currentFrame);
        sprite.setPosition(getBody().getPosition().x + 1.6f, getBody().getPosition().y + 1.6f);
        return sprite;
    }

    public void setRunnerState(RunnerState runnerState) {

        switch (runnerState){
            case PUNCH:
                AudioManager.instance.playSound(SOUND.PUNCH);
                break;
            case KICK:
                AudioManager.instance.playSound(SOUND.KICK);
                break;
            case FINAL:
                break;
        }

        if(runnerState == RunnerState.FINAL && attactSinceFinal < ATTACKS_TILL_FINAL){
            return;
        }

        this.runnerState = runnerState;

        if(runnerState == RunnerState.FINAL){
            attactSinceFinal = 0;
        }
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
