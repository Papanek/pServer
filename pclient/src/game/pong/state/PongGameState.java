package game.pong.state;

import game.Drawable;
import game.GameObject;
import game.GameState;

import java.awt.*;
import java.util.LinkedList;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class PongGameState implements java.io.Serializable, GameState {
    private LinkedList<GameObject> gameObjects;

    public PongGameState(int width, int height) {
        gameObjects = new LinkedList<>();
        gameObjects.add(new PongPlayer(1, 10, 10));
        gameObjects.add(new PongPlayer(1, 500, 10));
        gameObjects.add(new PongBall(width / 2, height / 2));
    }

    @Override
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    @Override
    public void update() {
        for (GameObject object : gameObjects) {
            object.update();
        }
    }

    @Override
    public void draw(Graphics2D g, float extrapolation) {
        for (GameObject object : gameObjects) {
            object.draw(g, extrapolation);
        }
    }

    @Override
    public String toString() {
        return "PongGameState{" +
                "gameObjects=" + gameObjects +
                '}';
    }
}