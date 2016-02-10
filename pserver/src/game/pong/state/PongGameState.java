package game.pong.state;

import engine.EngineConstants;
import game.*;
import network.Server;

import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class PongGameState extends GameState {
    public PongGameState(){
        gameObjects.add(new PongBall(EngineConstants.GAME_WIDTH/2,EngineConstants.GAME_HEIGHT/2));
        addPlayerTwo("2");addPlayerOne("1");
        //addPlayerOne(Server.getInstance().getConnections().getConnection(0).getId());
        //addPlayerTwo(Server.getInstance().getConnections().getConnection(1).getId());
    }

    public void addPlayerOne(String id){
        gameObjects.add(new PongPlayer(id,10,EngineConstants.GAME_HEIGHT/2));
    }
    public void addPlayerTwo(String id){
        gameObjects.add(new PongPlayer(id,EngineConstants.GAME_WIDTH- GameConstants.PLAYER_WIDTH-10,EngineConstants.GAME_HEIGHT/2));
    }

    @Override
    public Vector<GameObject> getGameObjects() {
        return gameObjects;
    }

    @Override
    public void update() {
        for (GameObject object: gameObjects){
            object.update();
        }
    }

    @Override
    public String toString() {
        return "PongGameState{" +
                "gameObjects=" + gameObjects +
                '}';
    }
}