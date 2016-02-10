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
    private int speedX, speedY;
    public PongBall(double x, double y) {
        super(x, y);
        setDimensions(GameConstants.BALL_WIDTH,GameConstants.BALL_HEIGHT );
        speedX = 5;
        speedY = 5;
    }

    @Override
    public void setDimensions(int width, int height) {
        this.width = width; this.height=height;
    }

    @Override
    public void update() {
        move();
    }

    private void move(){
        this.x += speedX;
        this.y += speedY;
        if(x>GameConstants.ARENA_WIDTH-getWidth()){
            speedX *= -1;
        } else if(x<0){
            speedX *= -1;
        }
        if(y>GameConstants.ARENA_HEIGHT-getHeight()){
            speedY *= -1;
        } else if(y<0){
            speedY *= -1;
        }
    }

    @Override
    public void draw(Graphics2D g, float extrapolation) {
        g.translate(x+speedX*extrapolation,y+speedY*extrapolation);
        g.fillOval(0,0,width,height);
        g.translate(-(x+speedX*extrapolation),-(y+speedY*extrapolation));
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
