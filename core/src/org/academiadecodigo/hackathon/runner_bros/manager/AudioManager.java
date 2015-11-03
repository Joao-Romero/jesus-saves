package org.academiadecodigo.hackathon.runner_bros.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by cadet on 31/10/15.
 */
public class AudioManager {
    public static Music gameMusic;
    public static Music menuMusic;
    public static Music gameOverMusic;
    private Music[] musics;
    public static Sound powerUp;
    public final boolean ON = false;

    public AudioManager() {
    }

    public void init() {
        //load sounds
        powerUp = loadSound("sounds/powerUp.mp3");

        // load and start playing music
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/game.mp3"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);

        // load and start playing music
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
        menuMusic.setLooping(true);
        menuMusic.setVolume(1f);

        // load and start playing music
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("music/over.mp3"));
        gameOverMusic.setLooping(true);
        gameOverMusic.setVolume(0.5f);

        musics = new Music[3];
        musics[0] = menuMusic;
        musics[1] = gameMusic;
        musics[2] = gameOverMusic;
    }



    private Sound loadSound(String file) {
        return Gdx.audio.newSound(Gdx.files.internal(file));
    }

    public void playSound(Sound sound) {
        if(ON){
            sound.play(0.5f);
        }

    }

    public void playMusic(Music music){
        for(Music m:musics){
            if(m != null && m.isPlaying()){
                m.stop();
            }
        }
        if(ON){
            music.setLooping(true);
            music.play();
        }

    }
}
