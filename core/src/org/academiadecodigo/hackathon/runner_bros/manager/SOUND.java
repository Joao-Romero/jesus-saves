package org.academiadecodigo.hackathon.runner_bros.manager;

/**
 * Created by campelo on 25/03/16.
 */
public enum SOUND {
    GAME,MENU,PUNCH,KICK;

    public String getPath(){
        return "sounds/"+ this.name().toLowerCase() + ".mp3";
    }
}
