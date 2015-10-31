package org.academiadecodigo.hackathon.runner_bros.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.box2d.MovingPlatform;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.*;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
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

    private Sprite sprite = new Sprite(AssetManager.instance.sonic);
    private Sprite sprite2 = new Sprite(AssetManager.instance.crash);
    private Sprite sprite3 = new Sprite(AssetManager.instance.mario);
    private Sprite sprite4 = new Sprite(AssetManager.instance.pikachu);
    private Animation animation;
    private Animation animation2;
    private Animation animation3;
    private Animation animation4;

    private TextureRegion currentFrame;
    private TextureRegion currentFrame2;
    private TextureRegion currentFrame3;
    private TextureRegion currentFrame4;

    private float stateTime;

    private World world;
    private Ground ground;
    private Runner runner;
    private Runner runner2;


    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    //private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameStage(){
        spriteBatch = new SpriteBatch();
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
        //renderer = new Box2DDebugRenderer();
    }


    private void setUpWorld() {
        world = WorldUtils.createWorld();
        // Let the world now you are handling contacts
        world.setContactListener(this);
        loadMap(1);

        setUpRunner();

        animation = AssetManager.instance.sonicAnimation;
        animation2 = AssetManager.instance.crashAnimation;
        animation3 = AssetManager.instance.marioAnimation;
        animation4 = AssetManager.instance.pikachuAnimation;

        sprite.setRegion(animation.getKeyFrame(0));
        sprite.setSize(1, 1);
        sprite2.setRegion(animation2.getKeyFrame(0));
        sprite2.setSize(1,1);
        sprite3.setRegion(animation3.getKeyFrame(0));
        sprite3.setSize(1,1);
        sprite4.setRegion(animation4.getKeyFrame(0));
        sprite4.setSize(1,1);
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

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            Wall wall = new Wall(WorldUtils.createWall(world, (rect.getX() + rect.getWidth() / 2) / 32,
                    (rect.getY() + rect.getHeight() / 2) / 32,
                    rect.getWidth() / 32,
                    rect.getHeight() / 32));

            addActor(wall);
        }

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            PowerUp powerUp = new PowerUp(WorldUtils.createPowerUp(world, (rect.getX() + rect.getWidth() / 2) / 32,
                    (rect.getY() + rect.getHeight() / 2) / 32,
                    rect.getWidth() / 32,
                    rect.getHeight() / 32, 0));

            addActor(powerUp);
        }


        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            FinishLine finishLine = new FinishLine(WorldUtils.createFinishLine(world, (rect.getX() + rect.getWidth() / 2) / 32,
                    (rect.getY() + rect.getHeight() / 2) / 32,
                    rect.getWidth() / 32,
                    rect.getHeight() / 32, 0));

            addActor(finishLine);
        }



    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world,10f,2f,1f,1f),RunnerType.sonic,sprite);
        runner2 = new Runner(WorldUtils.createRunner(world, 10f, 2f, 1f, 1f),RunnerType.crash,sprite);
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

    private Runner getFrontRunner(){

        Runner result = null;

        for(Actor actor:getActors()){
            if(!(actor instanceof Runner)){
                continue;
            }
            result = (result == null || ((Runner)actor).getBodyPositionX() > ((Runner)result).getBodyPositionX()) ? (Runner) actor : result;
        }
        return result;
    }

    @Override
    public void draw() {
        super.draw();

        Runner frontRunner = getFrontRunner();

        camera.position.set(frontRunner.getBodyPositionX() + 3, 6.5f, 0f);
        camera.update();

        sprite.setPosition(runner.getBody().getPosition().x + 1.6f, runner.getBody().getPosition().y + 1.6f);
        sprite2.setPosition(runner2.getBody().getPosition().x + 1.6f, runner2.getBody().getPosition().y + 1.6f);
        //sprite3.setPosition(runner3.getBody().getPosition().x + 1.6f, runner3.getBody().getPosition().y + 1.6f);
        //sprite4.setPosition(runner4.getBody().getPosition().x + 1.6f, runner4.getBody().getPosition().y + 1.6f);




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
        //renderer.render(world, camera.combined);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        stateTime+=Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime,true);
        currentFrame2 = animation2.getKeyFrame(stateTime,true);
        //currentFrame3 = animation3.getKeyFrame(stateTime,true);
        //currentFrame4 = animation4.getKeyFrame(stateTime,true);

        sprite.setRegion(currentFrame);
        sprite2.setRegion(currentFrame2);
        //sprite3.setRegion(currentFrame3);
        //sprite4.setRegion(currentFrame4);
        sprite.draw(spriteBatch);
        sprite2.draw(spriteBatch);
        //sprite3.draw(spriteBatch);
        //sprite4.draw(spriteBatch);
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
                runner.landed();
                System.out.println("Hit ground");
                break;
            case WALL:
                runner.setNextToWall(true);
                System.out.println("Hit wall");
                break;
            case RUNNER:
                runner.setNextToRunner(true);
                Runner runner2 = getRunnerFromBody(orderedBody[1]);
                runner2.setNextToRunner(true);
                break;
            case POWERUP:
                System.out.println("powered");
                getFrontRunner().getBody().applyLinearImpulse(new Vector2(-2.5f, 0f), runner.getBody().getWorldCenter(), true);
                getOtherRunner(getFrontRunner()).getBody().applyLinearImpulse(new Vector2(1f, 1f), runner.getBody().getWorldCenter(), true);
                Main.audioManager.playSound(AudioManager.powerUp);
                break;
            case FINISHLINE:
                Main.gameManager.setWinner(runner);
                System.out.println("finished");
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
