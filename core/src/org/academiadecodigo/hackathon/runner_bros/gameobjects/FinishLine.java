package org.academiadecodigo.hackathon.runner_bros.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserData;
import org.academiadecodigo.hackathon.runner_bros.box2d.UserDataType;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by cadet on 31/10/15.
 */
public class FinishLine extends GameActor{
    public FinishLine(World world,Rectangle rectangle) {
        super();
        float x = (rectangle.getX()+rectangle.getWidth()/2f)/32f;
        float y = (rectangle.getY()+rectangle.getHeight()/2f)/32f;
        float width = rectangle.getWidth()/32f;
        float height = rectangle.getHeight()/32f;


        userData = new UserData(UserDataType.FINISHLINE);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(x,y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/ 2,height/ 2);
        Fixture fixture = body.createFixture(shape, Constants.WALL_DENSITY);
        fixture.setRestitution(0);
        fixture.setSensor(true);
        body.setUserData(new UserData(UserDataType.FINISHLINE));
        shape.dispose();
        setBody(body);
    }
}
