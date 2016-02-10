package game;

import java.util.Vector;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/9/2016
 * ******************************
 **/
public abstract class GameState implements Updatable, java.io.Serializable {
    protected Vector<GameObject> gameObjects;

    public GameState(){
        gameObjects = new Vector<>();
    }

    public abstract Vector<GameObject> getGameObjects();
}
