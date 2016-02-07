package game.pong.state;

import game.Sizable;
import game.GameConstants;
import game.GameObject;
import game.Updatable;

import java.awt.*;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public class PongPlayer extends GameObject implements Sizable, Updatable {
    private int id;
    private double speedY;
    private int width, height;

    public PongPlayer(int id, int x, int y) {
        super(x, y);
        this.id = id;
        speedY = 0;
        width = GameConstants.PLAYER_WIDTH;
        height = GameConstants.PLAYER_HEIGHT;
        speedY = 1;
    }

    @Override
    public void update() {
        move();
    }

    private void move() {
        this.y += speedY;
        if (y > GameConstants.ARENA_HEIGHT - getHeight()) {
            speedY *= -1;
        } else if (y < 0) {
            speedY *= -1;
        }
    }

    @Override
    public void draw(Graphics2D g, float extrapolation) {
        g.translate(x, y + speedY * extrapolation);
        g.fillRect(0, 0, width, height);
        g.translate(-x, -(y + speedY * extrapolation));
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "PongPlayer{" +
                "ID='" + id + '\'' +
                ", speedY=" + speedY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
