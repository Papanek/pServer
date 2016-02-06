package game.pong.state;

import game.Sizable;
import game.GameObject;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public class PongBall extends GameObject implements Sizable {
    private int width, height;
    private int speedX, speedY;
    public PongBall(int x, int y) {
        super(x, y);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
