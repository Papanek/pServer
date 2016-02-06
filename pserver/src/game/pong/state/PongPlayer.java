package game.pong.state;

import game.Sizable;
import game.GameConstants;
import game.GameObject;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public class PongPlayer extends GameObject implements Sizable {
    private String name;
    private double speedY;
    private int width, height;
    public PongPlayer(String name, int x, int y){
        super(x,y);
        this.name = name;
        speedY = 0;
        width  = GameConstants.PLAYER_WIDTH;
        height = GameConstants.PLAYER_HEIGHT;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
