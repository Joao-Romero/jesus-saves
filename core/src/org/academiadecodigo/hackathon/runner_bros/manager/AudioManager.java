package org.academiadecodigo.hackathon.runner_bros.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Created by cadet on 31/10/15.
 */
public class AudioManager {

    public static Music gameMusic;
    public static Music menuMusic;
    public static Music gameOverMusic;
    public static Sound powerUp;

    private HashMap<SOUND,Sound> sounds;
    private HashMap<SOUND,Music> musics;

    public final boolean ON = false;

    public static final AudioManager instance = new AudioManager();

    private AudioManager(){
        init();
    }

    private void init() {
        sounds = new HashMap();
        musics = new HashMap();

        //load sounds
        // load and start playing music
        Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal(SOUND.GAME.getPath()));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);

        musics.put(SOUND.GAME,gameMusic);

        // load and start playing music
        Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal(SOUND.MENU.getPath()));
        menuMusic.setLooping(true);
        menuMusic.setVolume(1f);

        musics.put(SOUND.MENU,menuMusic);

        /*
        // load and start playing music
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("music/over.mp3"));
        gameOverMusic.setLooping(true);
        gameOverMusic.setVolume(0.5f);
        */

        /*
        musics = new Music[3];
        musics[0] = menuMusic;
        musics[1] = gameMusic;
        */
        //musics[2] = gameOverMusic;


        sounds.put(SOUND.PUNCH,Gdx.audio.newSound(Gdx.files.internal(SOUND.PUNCH.getPath())));
        sounds.put(SOUND.KICK,Gdx.audio.newSound(Gdx.files.internal(SOUND.KICK.getPath())));
   }



    public Sound loadSound(String file) {
        return Gdx.audio.newSound(Gdx.files.internal(file));
    }

    public void playSound(SOUND sound) {
        if(ON){
            Sound sound1 = sounds.get(sound);
            sound1.play();
        }
    }

    public void playMusic(SOUND music) {
        if(ON){
            musics.get(music).play();
        }
    }

}
