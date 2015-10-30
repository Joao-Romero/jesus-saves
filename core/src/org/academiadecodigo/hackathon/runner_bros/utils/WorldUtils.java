package org.academiadecodigo.hackathon.runner_bros.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.runner_bros.box2d.GroundUserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.RunnerUserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.WallUserData;

/**
 * Created by cadet on 30/10/15.
 */
public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

   /* public static Body createGround(World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        Fixture fixture = body.createFixture(shape, Constants.GROUND_DENSITY);
        //fixture.setRestitution(0.4f);
        //fixture.setFriction(8f);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }*/

    public static Body createWall(World world, float x, float y, float width, float height) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        Fixture fixture = body.createFixture(shape, Constants.WALL_DENSITY);
        body.setUserData(new WallUserData());
        shape.dispose();
        return body;
    }

    public static Body createGround(World world, float x, float y, float width, float height, float restituition) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        Fixture fixture = body.createFixture(shape, Constants.WALL_DENSITY);
        fixture.setRestitution(restituition);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createMovingPlatform(World world, float x, float y, float width, float height, float restituition) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        Fixture fixture = body.createFixture(shape, Constants.GROUND_DENSITY);
        fixture.setRestitution(restituition);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createRunner(World world, float x, float y, float width, float height) {


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y));
        PolygonShape shape = new PolygonShape();
        //CircleShape shape = new CircleShape();
        shape.setAsBox(0.5f, 0.5f, new Vector2(2, 2), 45);
        //shape.setRadius(0.2f);
        Body body = world.createBody(bodyDef);

        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        Fixture fixture =  body.createFixture(shape, Constants.RUNNER_DENSITY);
        body.setFixedRotation(true);
        fixture.setFriction(2f);



        body.resetMassData();
        body.setUserData(new RunnerUserData());
        shape.dispose();

        body.setBullet(true);

        return body;
    }

    /*public static Body createRunner(World world, float x, float y, float width, float height) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1f);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);



        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.createFixture(shape, Constants.RUNNER_DENSITY);

        body.resetMassData();
        body.setUserData(new RunnerUserData());
        shape.dispose();
        return body;
    }*/



}

