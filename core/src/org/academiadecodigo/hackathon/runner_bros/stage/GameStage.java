package org.academiadecodigo.hackathon.runner_bros.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Runner;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerState;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerType;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.screens.GameScreen;
import org.academiadecodigo.hackathon.runner_bros.utils.BodyUtils;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;


/**
 * Created by cadet on 30/10/15.
 */
public class GameStage extends Stage implements ContactListener, InputProcessor {

    public static final int DAMAGE_DEALT = 5;
    private SpriteBatch spriteBatch;
    private float stateTime;

    private World world;
    private Runner runner;
    private Runner runner2;
    private Runner winner;

    private RunnerType lastAttackingRunnerType;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    //private Box2DDebugRenderer renderer = new Box2DDebugRenderer();

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;

    public GameStage() {
        spriteBatch = new SpriteBatch();
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
    }


    private void setUpWorld() {
        world = new World(Constants.WORLD_GRAVITY, true);

        // Let the world now you are handling contacts
        world.setContactListener(this);
        setUpRunner();
    }

    private void setUpRunner() {

        runner = new Runner(world, Main.gameManager.getRunnerType());
        runner2 = new Runner(world, Main.gameManager.getRunnerType2());
        /*
        runner.setGreenBar(GameScreen.g1Bar);
        runner.setRedBar(GameScreen.r1Bar);

        runner2.setGreenBar(GameScreen.g2Bar);
        runner2.setRedBar(GameScreen.r2Bar);
        */
        addActor(runner);
        addActor(runner2);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
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

        //renderer.render(world, camera.combined);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        stateTime += Gdx.graphics.getDeltaTime();

        (runner.getSpriteFrame(stateTime, true)).draw(spriteBatch);
        (runner2.getSpriteFrame(stateTime, true)).draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int key) {
        float impulseX = 0f;
        float impulseY = 0f;

        final float damageRadius = 0.5f;

        Runner currentRunner = null;
        RunnerState runnerState = RunnerState.STAND;

        switch (key) {
            case Input.Keys.A:
                currentRunner = runner;
                impulseX = -2f;
                break;
            case Input.Keys.W:
                currentRunner = runner;
                runnerState = RunnerState.KICK;
                lastAttackingRunnerType = currentRunner.getType();
                currentRunner.changeBox(damageRadius);
                break;
            case Input.Keys.S:
                runnerState = RunnerState.PUNCH;
                currentRunner = runner;
                lastAttackingRunnerType = currentRunner.getType();
                currentRunner.changeBox(damageRadius);
                break;
            case Input.Keys.D:
                currentRunner = runner;
                impulseX = 2f;
                break;
            case Input.Keys.E:
                currentRunner = runner;
                runnerState = RunnerState.DEFENSE;
                break;
            case Input.Keys.X:
                currentRunner = runner;
                runnerState = RunnerState.FINAL;
                currentRunner.inflictDamageTo(runner2,DAMAGE_DEALT);
                lastAttackingRunnerType = currentRunner.getType();
                break;
            case Input.Keys.UP:
                currentRunner = runner2;
                runnerState = RunnerState.KICK;
                lastAttackingRunnerType = currentRunner.getType();
                currentRunner.changeBox(damageRadius);
                break;
            case Input.Keys.DOWN:
                currentRunner = runner2;
                runnerState = RunnerState.PUNCH;
                currentRunner.changeBox(damageRadius);
                lastAttackingRunnerType = currentRunner.getType();
                break;
            case Input.Keys.LEFT:
                currentRunner = runner2;
                impulseX = -2f;
                break;
            case Input.Keys.RIGHT:
                currentRunner = runner2;
                impulseX = 2f;
                break;
            case Input.Keys.ALT_RIGHT:
                currentRunner = runner2;
                runnerState = RunnerState.DEFENSE;
                break;
            case Input.Keys.PERIOD:
                currentRunner = runner2;
                runnerState = RunnerState.FINAL;
                currentRunner.inflictDamageTo(runner,DAMAGE_DEALT);
                lastAttackingRunnerType = currentRunner.getType();
                break;
            default:
                currentRunner = runner;
                runnerState = RunnerState.STOPPED;
                break;

        }

        /*
        if(runnerState.equals(RunnerState.FINAL)){
            currentRunner.inflictDamageTo(runner,DAMAGE_DEALT);
        }
        */

        currentRunner.setRunnerState(runnerState);
        currentRunner.getBody().applyLinearImpulse(new Vector2(impulseX,impulseY),currentRunner.getBody().getWorldCenter(),true);
        return true;
    }

    @Override
    public boolean keyUp(int key) {
        switch (key){
            case Input.Keys.D:
            case Input.Keys.A:
            case Input.Keys.S:
            case Input.Keys.W:
            case Input.Keys.E:
            case Input.Keys.X:
                runner.setRunnerState(RunnerState.STOPPED);
                runner.changeBox(0f);
                break;
            case Input.Keys.UP:
            case Input.Keys.DOWN:
            case Input.Keys.LEFT:
            case Input.Keys.RIGHT:
            case Input.Keys.ALT_RIGHT:
            case Input.Keys.PERIOD:
                runner2.setRunnerState(RunnerState.STOPPED);
                runner2.changeBox(0f);
                break;
        }
        return true;
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        Runner runner = getRunnerFromBody(a);
        Runner otherRunner = getRunnerFromBody(b);

        if (runner == null || otherRunner == null) {
            return;
        }

        //System.out.println(runner.getType() + " " + runner.getRunnerState());
        //System.out.println(otherRunner.getType() + " " + otherRunner.getRunnerState());
        //System.out.println(lastAttackingRunnerType);
        if (runner.getRunnerState().isAttack()){
            //runner2.changeLifeBy(runner.getRunnerState(), DAMAGE_DEALT);
            runner.inflictDamageTo(runner2,DAMAGE_DEALT);
            System.out.println(runner2.getType() + " " + runner2.getLife());
        }

        if (runner2.getRunnerState().isAttack()){
            //runner.changeLifeBy(runner2.getRunnerState(), DAMAGE_DEALT);
            runner2.inflictDamageTo(runner,DAMAGE_DEALT);
            System.out.println(runner.getType() + " " + runner.getLife());
        }

        if(runner.isDead()){
            winner = runner2;
        }

        if(runner2.isDead()){
            winner = runner;
        }



    }

    public Runner getWinner() {
        return winner;
    }

    public Runner getRunnerFromBody(Body body) {
        for (Actor actor : getActors()) {
            if (!(actor instanceof Runner)) {
                continue;
            }

            Runner runner = (Runner) actor;
            if (runner.getBody() == body) {
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
        UserDataType userDataType = ((UserData) orderedBody[1].getUserData()).getUserDataType();
        if (runner == null) {
            return;
        }

        switch (userDataType) {
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
                break;
            default:
                break;
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //empty method
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //empty method
    }
}
