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

    public static final String TEXTURE_ATLAS = "images/pack.atlas";

    public static final AssetManager instance = new AssetManager();

    public Skin skin;
    public Image menuImage;

    public TextureAtlas.AtlasRegion sonic;
    public Animation sonicAnimation;

    public HashMap<RunnerType,Sprite> runnerSprites;

    private AssetManager(){
        init();
    }

    public void init(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        menuImage = new Image(new Texture(Gdx.files.internal("images/logo.png")));

        sonic = atlas.findRegion("sonic");
       /*
        runnerSprites = new HashMap();
        for(RunnerType runnerType:RunnerType.values()){
            runnerSprites.put(runnerType,new Sprite(atlas.findRegion(runnerType.toString())));
        }
        */
    }

    private Animation getAnimation(TextureAtlas.AtlasRegion atlasRegion, int columns, int rows, float fps) {

        TextureRegion[][] temp =  atlasRegion.split(atlasRegion.getRegionWidth() / columns, atlasRegion.getRegionHeight() / rows);
        TextureRegion[] frames = new TextureRegion[columns * rows];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frames[index++] = temp[i][j];
            }
        }

        return new Animation(fps, frames);
    }

}
