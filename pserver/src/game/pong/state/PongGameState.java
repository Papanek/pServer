package game.pong.state;

import engine.EngineConstants;
import game.Drawable;
import game.GameConstants;
import game.GameObject;
import game.GameState;
import network.Server;

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
    public PongGameState(){
        gameObjects = new LinkedList<>();
        gameObjects.add(new PongBall(EngineConstants.GAME_WIDTH/2,EngineConstants.GAME_HEIGHT/2));
        addPlayerOne(Server.getInstance().getConnections().getConnection(0).getId());
        addPlayerTwo(Server.getInstance().getConnections().getConnection(1).getId());
    }

    public void addPlayerOne(String id){
        gameObjects.add(new PongPlayer(id,10,EngineConstants.GAME_HEIGHT/2));
    }
    public void addPlayerTwo(String id){
        gameObjects.add(new PongPlayer(id,EngineConstants.GAME_WIDTH- GameConstants.PLAYER_WIDTH-10,EngineConstants.GAME_HEIGHT/2));
    }

    @Override
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    @Override
    public void update() {
        for (GameObject object: gameObjects){
            object.update();
        }
    }

    @Override
    public void draw(Graphics2D g, float extrapolation) {
        for (GameObject object: gameObjects){
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