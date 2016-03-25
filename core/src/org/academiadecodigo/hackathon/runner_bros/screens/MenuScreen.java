package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import org.academiadecodigo.hackathon.runner_bros.Main;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.RunnerType;
import org.academiadecodigo.hackathon.runner_bros.manager.AssetManager;
import org.academiadecodigo.hackathon.runner_bros.manager.AudioManager;
import org.academiadecodigo.hackathon.runner_bros.manager.SOUND;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

import java.util.ArrayList;

/**
 * Created by cadet on 29/10/15.
 */
public class MenuScreen implements Screen {

    private Stage stage;
    private VerticalGroup verticalGroup;
    private CheckBoxListener checkBoxListener;

    private Label pressQ;
    private Label pressK;

    private static ArrayList<CheckBox> checked = new ArrayList(4);

    @Override
    public void show(){

        AudioManager.instance.playMusic(SOUND.MENU);
        stage = new Stage();
        Skin skin = AssetManager.instance.skin;

        Image logo = AssetManager.instance.logoImage;
        logo.setPosition((Constants.APP_WIDTH - logo.getWidth()) / 2, (Constants.APP_HEIGHT - logo.getHeight()) / 2);

        stage.addActor(logo);

        verticalGroup = new VerticalGroup();
        verticalGroup.align(Align.left);
        checkBoxListener = new CheckBoxListener();
        pressQ = new Label("<Press Q to add player>",skin);
        pressK = new Label("<Press K to add player>",skin);

        CheckBox checkBox;
        for(RunnerType runnerType:RunnerType.values()){
            checkBox = new CheckBox(" "+runnerType,skin);
            checkBox.setName(runnerType.toString());
            checkBox.getCells().get(0).size(20, 20);
            checkBox.setSize(200, 40);
            checkBox.align(Align.left);
            checkBox.addListener(checkBoxListener);

            verticalGroup.addActor(checkBox);
        }

        //verticalGroup.addActor(pressQ);
        //verticalGroup.addActor(pressK);
        verticalGroup.setPosition(420,200);
        stage.addActor(verticalGroup);


        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        checkBoxListener.updateChecked();
        int numberChecked = 0;
        CheckBox tempCheckBox;
        for(Actor actor:verticalGroup.getChildren()){
            if(!(actor instanceof CheckBox)){
                continue;
            }
            tempCheckBox = (CheckBox) actor;

            numberChecked += tempCheckBox.isChecked()? 1:0;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            pressQ.remove();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            pressK.remove();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && checked.size() == 2) {
            Main.gameManager.setRunnerType(RunnerType.getByName(checked.get(0).getName()));
            Main.gameManager.setRunnerType2(RunnerType.getByName(checked.get(1).getName()));
            Main.gameManager.setScreen(Main.gameScreen);
            Gdx.input.setInputProcessor(Main.gameScreen.getStage());
        }

        stage.draw();
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

    public class CheckBoxListener extends InputListener{
        /*public CheckBoxListener(){
            checked = new ArrayList(4);
        }*/

        public void touchUp(InputEvent event,float x,float y,int pointer,int button){
            System.out.println("c");
        }

        public void updateChecked(){
            CheckBox tempCheckBox;
            for(Actor actor:verticalGroup.getChildren()){

                if(!(actor instanceof CheckBox)){
                   continue;
                }
                tempCheckBox = (CheckBox) actor;
                if(tempCheckBox.isChecked() && !checked.contains(tempCheckBox)) {
                    checked.add(tempCheckBox);
                    continue;
                }
                if(!tempCheckBox.isChecked() && checked.contains(tempCheckBox)){
                    checked.remove(tempCheckBox);
                    continue;
                }
            }
        }

        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            updateChecked();

            CheckBox checkBox = (CheckBox) event.getListenerActor();
            CheckBox tempCheckBox;
            int numberChecked = 0;
            for(Actor actor:verticalGroup.getChildren()){
                if(!(actor instanceof CheckBox)){
                    continue;
                }
                tempCheckBox = (CheckBox) actor;

                numberChecked += tempCheckBox.isChecked()? 1:0;
            }

            if(numberChecked == 2 && !checked.contains(checkBox)){
                checked.get(0).setChecked(false);
            }
            return true;
        }
    }
}
