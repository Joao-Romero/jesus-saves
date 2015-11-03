package org.academiadecodigo.hackathon.runner_bros.gameobjects;

/**
 * Created by cadet on 30/10/15.
 */
public enum RunnerType {
    sonic,crash,mario,pikachu;

    public static RunnerType getByName(String name){
        for(RunnerType runnerType:RunnerType.values()){
            if(runnerType.toString().equals(name)){
                return runnerType;
            }
        }
        return null;
    }
}
