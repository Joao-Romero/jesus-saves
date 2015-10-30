package org.academiadecodigo.hackathon.runner_bros.box2d;

import com.badlogic.gdx.math.Vector2;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by cadet on 30/10/15.
 */
public class RunnerUserData extends UserData {

    private Vector2 jumpingLinearImpulse;
    private Vector2 runningRightLinearImpulse;
    private Vector2 runningLeftLinearImpulse;

    private final Vector2 runningPosition = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);

    public RunnerUserData() {
        super();
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        runningRightLinearImpulse = Constants.RUNNER_RUNNING_LINEAR_IMPULSE_RIGHT;
        runningLeftLinearImpulse = Constants.RUNNER_RUNNING_LINEAR_IMPULSE_LEFT;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public Vector2 getRunningRightLinearImpulse() {
        return runningRightLinearImpulse;
    }

    public Vector2 getRunningLeftLinearImpulse() {
        return runningLeftLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }

    public float getDodgeAngle() {
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }

}
