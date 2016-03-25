package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

/**
 * Created by codecadet on 24/03/16.
 */
public class CreditsScreen extends AbstractScreen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private long startTime;
    private String textCredits;
    BitmapFont bitmapFont;

    boolean startRotation = false;


    double m = 0.002;


    public CreditsScreen(Game game) {
        super(game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        int textX = 500;
        int textY = 500;


        if (camera.zoom >= 1.7 || camera.zoom <= 0.01) {
            m = m * (-1);
        }

        if (camera.zoom >= 1.7) {
            startRotation = true;
        }


        if (startRotation) {

            camera.rotate(1);

        }

        camera.zoom -= m;


        camera.update();

        batch.begin();
        //batch.draw(, 0, 0);
        bitmapFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        bitmapFont.draw(batch, textCredits, textX, textY);

        batch.end();


        //close splash screen after some time, or when touched after 2 seconds
        if (Gdx.input.justTouched()) {


            //dispose();
            //ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU,game);
        }

    }

    @Override
    public void show() {
        //splashBackground = new Texture(Config.ASSETS_PATH + "splashScreen.jpg");
        startTime = TimeUtils.millis();


        textCredits = "Campilers Team \n\n" +
                "Nuno Campelo \n" +
                "Miguel Chambel \n" +
                "Itamar Lourenço\n" +
                "João Romero \n" +
                "Joana Falcão\n" +
                "Joana Vasconcelos\n" +
                "Milena Baeza\n" +
                "André Santos\n" +
                "Mariana Gaspar";
        bitmapFont = new BitmapFont();


    }

    @Override
    public void dispose() {

        batch.dispose();
    }
}
