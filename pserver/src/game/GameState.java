package game;

import java.util.Vector;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/9/2016
 * ******************************
 **/
public abstract class GameState implements Drawable, Updatable {
    protected Vector<GameObject> gameObjects;

    public GameState(){
        gameObjects = new Vector<>();
    }

    protected abstract Vector<GameObject> getGameObjects();
}
