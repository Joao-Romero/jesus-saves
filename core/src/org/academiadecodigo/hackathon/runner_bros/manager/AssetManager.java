package org.academiadecodigo.hackathon.runner_bros.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerState;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerType;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

import java.util.HashMap;

/**
 * Created by cadet on 30/10/15.
 */
public class AssetManager {

    public static final AssetManager instance = new AssetManager();
    public Skin skin;
    public Image menuImage;
    public Image bgImage;
    public Image sonicWinner;

    public HashMap<RunnerType,TextureAtlas.AtlasRegion> regions;
    public HashMap<RunnerType,HashMap<RunnerState,Animation>> animations;

    private AssetManager(){
        init();
    }

    public void init(){

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        menuImage = new Image(new Texture(Gdx.files.internal("images/logo.png")));
        bgImage = new Image(new Texture(Gdx.files.internal("images/bg/background.png")));
        bgImage.setWidth(Constants.APP_WIDTH);
        bgImage.setHeight(Constants.APP_HEIGHT);

        animations = new HashMap<RunnerType, HashMap<RunnerState, Animation>>();
        regions = new HashMap();


        for(RunnerType runnerType:RunnerType.values()){

            TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("animations/"+runnerType.toString().toLowerCase()+".atlas"));

            animations.put(runnerType, new HashMap<RunnerState, Animation>());

            TextureAtlas.AtlasRegion[] array = null;
            TextureRegion[] region = null;
            for(RunnerState runnerState:RunnerState.values()){

                int frames = runnerState.getFrames();
                array = new TextureAtlas.AtlasRegion[frames];
                region = new TextureRegion[frames];

                for(int i = 0; i < frames; i++){
                    array[i] = atlas.findRegion(runnerState.name().toLowerCase() + Integer.toString(i));
                    region[i] = array[i];

                    if (runnerType.equals(RunnerType.ROMERO)){
                        region[i].flip(true,false);
                    }

                }

                Animation stateAnimation = new Animation(1/frames, array);
                stateAnimation.setPlayMode(Animation.PlayMode.LOOP);
                animations.get(runnerType).put(runnerState,stateAnimation);

            }

            regions.put(runnerType,array[0]);
        }

    }
}
