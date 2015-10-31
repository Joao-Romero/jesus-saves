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


    public static final String TEXTURE_ATLAS = "images/pack3.atlas";


    public static final AssetManager instance = new AssetManager();

    public Skin skin;
    public Image menuImage;

    public TextureAtlas.AtlasRegion sonic;
    public TextureAtlas.AtlasRegion crash;


    public Animation sonicAnimation;
    public Animation crashAnimation;


    private AssetManager(){
        init();
    }

    public void init(){

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        menuImage = new Image(new Texture(Gdx.files.internal("images/logo.png")));

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS));

        sonic = atlas.findRegion("sonic_run1");
        crash = atlas.findRegion("crash_run1");


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


        sonicAnimation = new Animation(1/24f, region);
        sonicAnimation.setPlayMode(Animation.PlayMode.LOOP);

        crashAnimation = new Animation(1/24f, region2);
        crashAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }
}
