package org.academiadecodigo.hackathon.runner_bros.manager;

import org.academiadecodigo.hackathon.runner_bros.gameobjects.GameObject;
import org.academiadecodigo.hackathon.runner_bros.gameobjects.Updatable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by cadet on 30/10/15.
 * Has list of game objects
 *
 */
public class GameObjectManager {

    private Stack<GameObject> objectsToAdd = new Stack<GameObject>();
    private Stack<GameObject> objectsToRemove = new Stack<GameObject>();



    private List<GameObject> gameObjectList = new ArrayList<GameObject>();

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }
    public void addGameObject(GameObject gameObject){
        objectsToAdd.push(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        objectsToRemove.add(gameObject);
    }

    /**
     *
     * @param deltaTime
     *
     * Removes an adds scheduled game objects
     * Updates all Updatable game object
     * Checks for collision between Collidable game objects
     *
     */
    public void update(float deltaTime){
        // add all new objects to the list
        while(objectsToAdd.size() > 0) {
            gameObjectList.add(objectsToAdd.pop());
        }

        // remove all objects that were destroyed
        while(objectsToRemove.size() > 0) {
            gameObjectList.remove(objectsToRemove.pop());
        }

        // updates all objects and checks
        // missing check for collisions
        if(gameObjectList.size() > 0){

            for(GameObject gameObject:gameObjectList){
                if(gameObject instanceof Updatable){
                    ((Updatable) gameObject).update(deltaTime);
                }

                /*
                if(gameObject instanceof Collidable) {
                    checkCollision(gameObject);
                }
                 */


            }

        }

    }
}
