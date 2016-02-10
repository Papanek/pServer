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
public class PongPlayer extends GameObject {
    private String id;
    private double speedY;
    public PongPlayer(String id, int x, int y){
        super(x,y);
        setDimensions(GameConstants.PLAYER_WIDTH,GameConstants.PLAYER_HEIGHT);
        this.id = id;
        speedY = 0;
        speedY = 1;
    }

    @Override
    public void setDimensions(int width, int height) {
        this.width = width; this.height = height;
    }

    @Override
    public void update() {
        move();
    }

    private void move(){
        this.y += speedY;
        if(y>GameConstants.ARENA_HEIGHT-getHeight()){
            speedY *= -1;
        } else if(y<0){
            speedY *= -1;
        }
    }

    @Override
    public void draw(Graphics2D g, float extrapolation) {
        g.translate(x,y+speedY*extrapolation);
        g.fillRect(0,0,width,height);
        g.translate(-x,-(y+speedY*extrapolation));
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
