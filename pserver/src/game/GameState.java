package game;

import java.util.LinkedList;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public interface GameState extends Drawable, Updatable {
    LinkedList<GameObject> getGameObjects();
}