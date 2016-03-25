package org.academiadecodigo.hackathon.runner_bros.gameobjects;

/**
 * Created by campelo on 25/03/16.
 */
public enum RunnerState {
    STAND(3),STOPPED(1),DEFENSE(1), FINAL(3), PUNCH(4), KICK(4);

    private int frames;

    RunnerState(int frames){
        this.frames = frames;
    }

    public int getFrames() {
        return frames;
    }

    public boolean isAttack(){
        return this.equals(FINAL) || this.equals(KICK) || this.equals(PUNCH);
    }

}
