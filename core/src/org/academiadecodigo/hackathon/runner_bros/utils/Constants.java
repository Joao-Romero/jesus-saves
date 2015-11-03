package org.academiadecodigo.hackathon.runner_bros.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by cadet on 30/10/15.
 */
public class Constants {

    public static final int APP_WIDTH = Gdx.graphics.getWidth();
    public static final int APP_HEIGHT = Gdx.graphics.getHeight();

    public static final int VIEWPORT_WIDTH = 20;
    public static final int VIEWPORT_HEIGHT = 13;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -9.8f);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 10f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final float WALL_X = 2;
    public static final float WALL_Y = 2;
    public static final float WALL_WIDTH = 5f;
    public static final float WALL_HEIGHT = 20f;
    public static final float WALL_DENSITY = 0f;

    public static final float RUNNER_X = 2;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 1f;
    public static final float RUNNER_HEIGHT = 2f;

    public static final float RUNNER_GRAVITY_SCALE = 3f;
    public static float RUNNER_DENSITY = 1f;

    public static final float RUNNER_MAX_VELOCITY = 20f;

    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 2f);
    public static final Vector2 RUNNER_RUNNING_LINEAR_IMPULSE_RIGHT = new Vector2(0.1f, 0);

    public static final float RUNNER_DODGE_X = 2f;
    public static final float RUNNER_DODGE_Y = 1.5f;



}
