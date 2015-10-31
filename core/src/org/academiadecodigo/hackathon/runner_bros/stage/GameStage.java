package org.academiadecodigo.hackathon.runner_bros.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.hackathon.runner_bros.box2d.MovingPlatform;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Ground;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.PowerUp;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Runner;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Wall;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.utils.BodyUtils;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;
import org.academiadecodigo.hackathon.runner_bros.utils.WorldUtils;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;

import static org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType.GROUND;

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
    private Runner runner;
    private Runner runner2;


    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;


    private void setUpWorld() {
        world = WorldUtils.createWorld();
        // Let the world now you are handling contacts
        world.setContactListener(this);
        loadMap(3);

        //setUpGround();
        //setUpPlatforms();
        //setUpWall();
        //setUpPowerUps();
        setUpRunner();
    }

    private void loadMap(int number) {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("images/Map" + number + ".tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/32f);
        System.out.println("Load map");


        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            ground = new Ground(WorldUtils.createGround(world, (rect.getX() + rect.getWidth() / 2) / 32,
                    (rect.getY() + rect.getHeight() / 2) / 32,
                    rect.getWidth() / 32,
                    rect.getHeight() / 32, 0));

            addActor(ground);
        }
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world, 0f, 2f, 100000, 2, 0));
        //ground2 = new Ground(WorldUtils.createGround(world, 17f , 2f, 10, 2, 1));
        //ground3 = new Ground(WorldUtils.createGround(world, 30f , 4f, 10, 0.5f, 0));
        addActor(ground);
    }

    private void setUpPlatforms() {
        MovingPlatform platform;
        for(int i = 0; i < 2000; i++){

            platform = new MovingPlatform(WorldUtils.createMovingPlatform(world, i * 10f, 10f, 7, 0.5f, 0));

            addActor(platform);
        }

    }

    private void setUpPowerUps(){
        PowerUp powerUp;
        for (int i = 0;i<2000;i++){
            powerUp = new PowerUp(WorldUtils.createPowerUp(world,i * 75f,4.2f,1,1,0));

            System.out.println(powerUp.getUserData().getUserDataType());

            addActor(powerUp);
        }
    }

    private void setUpWall(){
        Wall wall;
        for(int i = 0; i < 2000; i++){
        wall = new Wall(WorldUtils.createWall(world, i*20, 3, 0.2f, 1.5f));
        addActor(wall);
        }
   }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world,2f,2f,1f,1f),sprite);
        runner2 = new Runner(WorldUtils.createRunner(world, 2f, 2f, 1f, 1f),sprite);
        addActor(runner);
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
        camera.position.set(runner.getBodyPositionX() + 3, runner.getBodyPositionY() + 5, 0f);
        camera.update();
        //Kryo kryo = new Kyro();

        for(Actor actor:getActors()){
            if(!(actor instanceof Runner)){
                continue;
            }

            Runner runner = (Runner) actor;
            if(runner.getBody().getLinearVelocity().x < Constants.RUNNER_MAX_VELOCITY && !runner.isNextToWall()){
                runner.getBody().applyLinearImpulse(Constants.RUNNER_RUNNING_LINEAR_IMPULSE_RIGHT, runner.getBody().getWorldCenter(), true);
            }

            if(runner.needsVerticalImpulse()){
                runner.getBody().applyLinearImpulse(Constants.RUNNER_JUMPING_LINEAR_IMPULSE, runner.getBody().getWorldCenter(), true);
                runner.setNeedsVerticalImpulse(false);
            }


        }

        mapRenderer.setView(camera);
        mapRenderer.render();
        renderer.render(world, camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(sprite, runner.getBodyPositionX(), runner.getBodyPositionY(), sprite.getWidth(), sprite.getHeight());
        spriteBatch.end();
        

    }




    @Override
    public boolean keyDown(int key){

        switch (key)
        {

            case Input.Keys.SPACE:
                if(runner.canJump()){
                    System.out.println("runner2 jumped");
                    runner.jump();
                }
                break;
            case Input.Keys.ENTER:
                if(runner2.canJump()){
                    System.out.println("runner jumped");
                    runner2.jump();
                }
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int key){

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
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        Body[] orderedBody = BodyUtils.order(a, b);

        Runner runner = getRunnerFromBody(orderedBody[0]);
        UserDataType userDataType = ((UserData)orderedBody[1].getUserData()).getUserDataType();


        if(runner == null){
            return;
        }

        switch (userDataType){
            case GROUND:
                //System.out.println(runner+" landed");
                runner.landed();
                break;
            case WALL:
                //System.out.println(runner +" walled");
                runner.setNextToWall(true);
                break;
            case RUNNER:
                //System.out.println(runner +" runned");
                runner.setNextToRunner(true);
                Runner runner2 = getRunnerFromBody(orderedBody[1]);
                runner2.setNextToRunner(true);
                break;
            case POWERUP:
                System.out.println("powered");
                getOtherRunner(runner).getBody().applyLinearImpulse(new Vector2(-5f, 0), runner.getBody().getWorldCenter(), true);
                break;
        }


    }

    private Runner getOtherRunner(Runner runner){
        for(Actor actor:getActors()){
            if(!(actor instanceof Runner) || actor == runner){
                continue;
            }
            return (Runner) actor;
        }
        return null;
    }

    public Runner getRunnerFromBody(Body body){
        for(Actor actor:getActors()){
            if(!(actor instanceof Runner)){
                continue;
            }

            Runner runner = (Runner) actor;
            if(runner.getBody() == body){
                return runner;
            }
        }
        return null;
    }


    @Override
    public void endContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        Body[] orderedBody = BodyUtils.order(a, b);
        Runner runner = getRunnerFromBody(orderedBody[0]);
        UserDataType userDataType = ((UserData)orderedBody[1].getUserData()).getUserDataType();
        if(runner == null){
            return;
        }

        switch (userDataType){
            case GROUND:
                //System.out.println(runner +" un landed");
                break;
            case WALL:
                //System.out.println(runner +" un walled");
                runner.setNextToWall(false);
                break;
            case RUNNER:
                //System.out.println(runner +" un runned");
                runner.setNextToRunner(false);
                Runner runner2 = getRunnerFromBody(orderedBody[1]);
                runner2.setNextToRunner(false);
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
