package org.academiadecodigo.hackathon.runner_bros.screens;

//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.HealthBar;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.manager.SOUND;
import org.academiadecodigo.hackathon.runner_bros.stage.GameStage;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by cadet on 30/10/15.
 */
public class GameScreen implements Screen {
    private GameStage stage;
    private Image bgImage;

    SpriteBatch batch;
    public static Texture g1Bar, r1Bar;
    public static Texture g2Bar, r2Bar;

    public HealthBar healthBar;

    private void initTestObjects() {

        int width = 1 ;
        int height = 1;
        Pixmap pixmap = createProceduralPixmap(width, height,0,1,0);
        Pixmap pixmap2 = createProceduralPixmap(width, height,1,0,0);

        g1Bar = new Texture(pixmap);
        r1Bar = new Texture(pixmap2);
        g2Bar = new Texture(pixmap);
        r2Bar = new Texture(pixmap2);

        healthBar = new HealthBar();
        stage.addActor(healthBar);

    }

    private Pixmap createProceduralPixmap (int width, int height,int r,int g,int b) {

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        pixmap.setColor(r, g, b, 1);
        pixmap.fill();

        return pixmap;
    }

    public GameStage getStage() {
        return stage;
    }


    @Override
    public void show(){
        AudioManager.instance.playMusic(SOUND.GAME);
        stage = new GameStage();
        Main.gameManager.startTimer();
        bgImage = AssetManager.instance.bgImage;

        batch = new SpriteBatch();
        initTestObjects();

    }

    @Override
    public void render(float delta){



        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        bgImage.setPosition((Constants.APP_WIDTH - bgImage.getWidth()) / 2, (Constants.APP_HEIGHT - bgImage.getHeight()) / 2);
        stage.addActor(bgImage);

        if(stage.getWinner() != null){
            //Main.gameManager.setScreen(Main.gameOverScreen);
            Main.gameManager.setScreen(Main.creditsScreen);
        }

        stage.draw();
        stage.act(delta);

        batch.begin();

        healthBar.draw(batch,1f);

        batch.draw(r1Bar,100,700,340,20);
        batch.draw(g1Bar,100,700,340,20);

        batch.draw(r2Bar,1024 - 340 - 100,700,340,20);
        batch.draw(g2Bar,1024 - 340 - 100,700,340,20);

        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        // empty
    }

    @Override
    public void pause() {
        // empty
    }

    @Override
    public void resume() {
        // empty
    }

    @Override
    public void hide() {
        // empty
    }

    @Override
    public void dispose() {
        // empty
    }
}
