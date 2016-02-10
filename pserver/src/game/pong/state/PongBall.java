package game.pong.state;

import game.GameConstants;
import game.GameObject;

import java.awt.*;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public class PongBall extends GameObject {
    public PongBall(double x, double y) {
        super(x, y);
        setDimensions();
        speedX = 2.5;
        speedY = 2.5;
    }

    @Override
    public void setDimensions() {
        this.width = GameConstants.BALL_WIDTH;
        this.height = GameConstants.BALL_HEIGHT;
    }

    @Override
    public void update() {
        move();
    }

    private void move(){
        this.x += speedX;
        this.y += speedY;
        if(getRightBound()>GameConstants.GAME_WIDTH){
            speedX *= -1;
        } else if(getLeftBound()<0){
            speedX *= -1;
        }
        if(getBottomBound()>GameConstants.GAME_HEIGHT){
            speedY *= -1;
        } else if(getTopBound()<0){
            speedY *= -1;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillOval(0,0,width,height);
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
