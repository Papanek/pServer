package game.pong.state;

import game.GameConstants;
import game.Sizable;
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
public class PongBall extends GameObject implements Sizable {
    private int width, height;
    private int speedX, speedY;

    public PongBall(int x, int y) {
        super(x, y);
        width = GameConstants.BALL_WIDTH;
        height = GameConstants.BALL_HEIGHT;
        speedX = 5;
        speedY = 5;
    }

    @Override
    public void update() {
        move();
    }

    private void move() {
        this.x += speedX;
        this.y += speedY;
        if (x > GameConstants.ARENA_WIDTH - getWidth()) {
            speedX *= -1;
        } else if (x < 0) {
            speedX *= -1;
        }
        if (y > GameConstants.ARENA_HEIGHT - getHeight()) {
            speedY *= -1;
        } else if (y < 0) {
            speedY *= -1;
        }
    }

    @Override
    public void draw(Graphics2D g, float extrapolation) {
        g.translate(x + speedX * extrapolation, y + speedY * extrapolation);
        g.fillOval(0, 0, width, height);
        g.translate(-(x + speedX * extrapolation), -(y + speedY * extrapolation));
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
        return "PongBall{" +
                "width=" + width +
                ", height=" + height +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                '}';
    }
}
