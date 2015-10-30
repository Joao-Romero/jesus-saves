package org.academiadecodigo.hackathon.runner_bros.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.academiadecodigo.hackathon.runner_bros.box2d.MovingPlatform;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Ground;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Runner;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Wall;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.utils.BodyUtils;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;
import org.academiadecodigo.hackathon.runner_bros.utils.WorldUtils;

/**
 * Created by cadet on 30/10/15.
 */
public class GameStage extends Stage implements ContactListener, InputProcessor {


    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private SpriteBatch spriteBatch;


    public GameStage(){
        spriteBatch = new SpriteBatch();
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
        renderer = new Box2DDebugRenderer();
    }


    private Sprite sprite = new Sprite(AssetManager.instance.sonic);


    private World world;
    private Ground ground;
    private Ground ground2;


    private Ground ground3;
    private MovingPlatform platform;
    private Wall wall;
    private Wall wall2;
    private Runner runner;
    private Runner runner2;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;


    private void setUpWorld() {
        world = WorldUtils.createWorld();
        // Let the world now you are handling contacts
        world.setContactListener(this);
        setUpGround();
        setUpPlatforms();
        setUpWall();
        setUpRunner();
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world, 0f, 2f, 100000, 2, 0));
        //ground2 = new Ground(WorldUtils.createGround(world, 17f , 2f, 10, 2, 1));
        //ground3 = new Ground(WorldUtils.createGround(world, 30f , 4f, 10, 0.5f, 0));
        addActor(ground);
    }

    private void setUpPlatforms() {

        for(int i = 0; i < 2000; i++){

            platform = new MovingPlatform(WorldUtils.createMovingPlatform(world, i * 10f, 10f, 7, 0.5f, 0));

            addActor(platform);
        }

    }

    private void setUpWall(){


        for(int i = 0; i < 2000; i++){
        wall2 = new Wall(WorldUtils.createWall(world, i*20, 3, 0.2f, 2));
        addActor(wall2);
        }
   }

    private void setUpRunner() {
        runner2 = new Runner(WorldUtils.createRunner(world, 2f, 2f, 1f, 1f),sprite);

        addActor(runner2);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation
    }


    @Override
    public void draw() {
        super.draw();
        camera.position.set(runner2.getBodyPositionX() + 3, runner2.getBodyPositionY() + 5, 0f);
        camera.update();


        //System.out.println(runner2.getBody().getLinearVelocity().x);
        if(runner2.getBody().getLinearVelocity().x < Constants.RUNNER_MAX_VELOCITY){
            runner2.getBody().applyLinearImpulse(Constants.RUNNER_RUNNING_LINEAR_IMPULSE_RIGHT, runner2.getBody().getWorldCenter(), true);
        }
        //runner2.getBody().setLinearVelocity(20f, 0);

        if(jumped){
            runner2.getBody().applyLinearImpulse(Constants.RUNNER_JUMPING_LINEAR_IMPULSE, runner2.getBody().getWorldCenter(), true);
            jumped = false;
        }

        /*
        if (runner2.isRunningRight()) {
            System.out.println("running right");

            //runner2.getBody().applyLinearImpulse(Constants.RUNNER_RUNNING_LINEAR_IMPULSE_RIGHT, runner2.getBody().getWorldCenter(), true);
            //runner2.getBody().getPosition().x -= 5 * Gdx.graphics.getDeltaTime();

        }
        */
        /*
        if (runner2.isRunningLeft()) {
            System.out.println("running left");

            runner2.getBody().applyLinearImpulse(Constants.RUNNER_RUNNING_LINEAR_IMPULSE_LEFT, runner2.getBody().getWorldCenter(), true);
            //runner2.getBody().getPosition().x += 5 * Gdx.graphics.getDeltaTime();
        }
        */


        renderer.render(world, camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(sprite, runner2.getBodyPositionX(), runner2.getBodyPositionY(), sprite.getWidth(), sprite.getHeight());
        spriteBatch.end();




    }


    private boolean jumped = false;

    @Override
    public boolean keyDown(int key){

        switch (key)
        {

            case Input.Keys.SPACE:


                if(!runner2.isJumping()){
                    System.out.println("space key");
                    runner2.jump();
                    jumped = true;
                }

                break;
            case Input.Keys.LEFT:
                System.out.println("key left down");
                runner2.setLeftMove(true);
                break;
            case Input.Keys.RIGHT:
                System.out.println("key right down");
                runner2.setRightMove(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int key){

        switch (key)
        {
            case Input.Keys.LEFT:
                runner2.setLeftMove(false);
                break;
            case Input.Keys.RIGHT:
                runner2.setRightMove(false);
                break;
        }
        return true;
    }



    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            //runner2.jump();

        }
        if (leftSideTouched(touchPoint.x, touchPoint.y)){
            //runner.runLeft();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        /*if (runner.isDodging()) {
            runner.stopDodge();
        }*/

        return super.touchUp(screenX, screenY, pointer, button);
    }


    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }


    @Override
    public void beginContact(Contact contact) {
        System.out.println("begin contact");
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {

            runner2.landed();
        }
    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
