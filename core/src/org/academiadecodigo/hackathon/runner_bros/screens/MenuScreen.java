package org.academiadecodigo.hackathon.runner_bros.screens;

import com.badlogic.gdx.Game;
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
import org.academiadecodigo.hackathon.runner_bros.manager.GameManager;
import org.academiadecodigo.hackathon.runner_bros.utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by cadet on 29/10/15.
 */
public class MenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private Game game;
    private VerticalGroup verticalGroup;
    private CheckBoxListener checkBoxListener;

    private static ArrayList<CheckBox> checked;

    public MenuScreen(Game game) {
        super();
        this.game = game;
    }

    @Override
    public void show(){



        Main.audioManager.playMusic(AudioManager.menuMusic);
        stage = new Stage();
        skin = AssetManager.instance.skin;
        //characters = new SelectBox<String>(skin);

        Image logo = AssetManager.instance.menuImage;
        logo.setPosition((Constants.APP_WIDTH - logo.getWidth()) / 2, (Constants.APP_HEIGHT - logo.getHeight()) / 2);
        stage.addActor(logo);

        verticalGroup = new VerticalGroup();
        verticalGroup.align(Align.left);
        checkBoxListener = new CheckBoxListener();
        CheckBox checkBox;
        for(RunnerType runnerType:RunnerType.values()){
            checkBox = new CheckBox(" "+runnerType,skin);
            checkBox.setName(runnerType.toString());
            checkBox.getCells().get(0).size(20, 20);
            checkBox.setSize(200, 40);
            checkBox.align(Align.left);
            checkBox.addListener(checkBoxListener);
            /*
            checkBox.addListener(new InputListener(){
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("c");
                }
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("d");
                    return true;
                }
            });
            */
            verticalGroup.addActor(checkBox);
            //checkBox.setChecked(runnerType.equals(RunnerType.sonic) || runnerType.equals(RunnerType.crash) ? true:false);
        }
        verticalGroup.setPosition(100,200);
        stage.addActor(verticalGroup);
        /*
        characters.setItems("sonic","crash","mario","pickachu");
        characters.setPosition(100,200);
        characters.setSize(150,40);
        stage.addActor(characters);
        */

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
            tempCheckBox = (CheckBox) actor;
            if(tempCheckBox == null){
                continue;
            }
            numberChecked += tempCheckBox.isChecked()? 1:0;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && numberChecked == 2) {
            Main.gameManager.setRunners(RunnerType.getByName(checked.get(0).getName()),RunnerType.getByName(checked.get(1).getName()));
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
        public CheckBoxListener(){
            checked = new ArrayList(4);
        }

        public void touchUp(InputEvent event,float x,float y,int pointer,int button){
            System.out.println("c");
        }

        public void updateChecked(){
            CheckBox tempCheckBox;
            for(Actor actor:verticalGroup.getChildren()){
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
                tempCheckBox = (CheckBox) actor;
                if(tempCheckBox == null){
                    continue;
                }
                numberChecked += tempCheckBox.isChecked()? 1:0;
            }
            /*
            if(numberChecked <2){
                return true;
            }

            if(checked.contains(checkBox)){
                //checkBox.toggle();
            } else {
                checked.get(0).setChecked(false);
            }
            */

            if(numberChecked == 2 && !checked.contains(checkBox)){
                checked.get(0).setChecked(false);
            }
            return true;
        }
    }
}
