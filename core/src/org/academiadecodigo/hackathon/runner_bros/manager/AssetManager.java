package org.academiadecodigo.hackathon.runner_bros.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerType;

import java.util.HashMap;

/**
 * Created by cadet on 30/10/15.
 */
public class AssetManager {


    public static final String TEXTURE_ATLAS = "images/pack4.atlas";


    public static final AssetManager instance = new AssetManager();

    public Skin skin;
    public Image menuImage;
    public Image sonicWinner;
    public Image crashWinner;
    public Image marioWinner;
    public Image pikachuWinner;


    public TextureAtlas.AtlasRegion sonic;
    public TextureAtlas.AtlasRegion crash;
    public TextureAtlas.AtlasRegion mario;
    public TextureAtlas.AtlasRegion pikachu;


    public Animation sonicAnimation;
    public Animation crashAnimation;
    public Animation marioAnimation;
    public Animation pikachuAnimation;



    private AssetManager(){
        init();
    }

    public void init(){

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        menuImage = new Image(new Texture(Gdx.files.internal("images/logo.png")));
        sonicWinner = new Image(new Texture(Gdx.files.internal("images/sonic.jpg")));
        crashWinner = new Image(new Texture(Gdx.files.internal("images/crash.jpg")));
        marioWinner = new Image(new Texture(Gdx.files.internal("images/mario.jpg")));
        pikachuWinner = new Image(new Texture(Gdx.files.internal("images/pikachu.jpg")));

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS));

        sonic = atlas.findRegion("sonic_run1");
        crash = atlas.findRegion("crash_run1");
        mario = atlas.findRegion("mario_run1");
        pikachu = atlas.findRegion("pikachu_run1");


        TextureAtlas.AtlasRegion[] sonic = new TextureAtlas.AtlasRegion[4];
        TextureRegion[] region = new TextureRegion[4];

        for(int i = 0; i < 4; i++){
            sonic[i] = atlas.findRegion("sonic_run" + Integer.toString(i + 1));
            region[i] = sonic[i];
        }

        TextureAtlas.AtlasRegion[] crash = new TextureAtlas.AtlasRegion[14];
        TextureRegion[] region2 = new TextureRegion[14];

        for(int i = 0; i < 14; i++){
            crash[i] = atlas.findRegion("crash_run" + Integer.toString(i + 1));
            region2[i] = crash[i];
        }

        TextureAtlas.AtlasRegion[] mario = new TextureAtlas.AtlasRegion[7];
        TextureRegion[] region3 = new TextureRegion[7];

        for(int i = 0; i < 7; i++){
            mario[i] = atlas.findRegion("mario_run" + Integer.toString(i + 1));
            region3[i] = mario[i];
        }

        TextureAtlas.AtlasRegion[] pikachu = new TextureAtlas.AtlasRegion[4];
        TextureRegion[] region4 = new TextureRegion[4];

        for(int i = 0; i < 4; i++){
            pikachu[i] = atlas.findRegion("pikachu_run" + Integer.toString(i + 1));
            region4[i] = pikachu[i];
        }


        sonicAnimation = new Animation(1/24f, region);
        sonicAnimation.setPlayMode(Animation.PlayMode.LOOP);

        crashAnimation = new Animation(1/24f, region2);
        crashAnimation.setPlayMode(Animation.PlayMode.LOOP);

        marioAnimation = new Animation(1/24f, region3);
        marioAnimation.setPlayMode(Animation.PlayMode.LOOP);

        pikachuAnimation = new Animation(1/24f, region4);
        pikachuAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }
}
