package game.pong.state;

import engine.GamePanel;
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
        setDimensions();
        this.id = id;
        speedY=0;
    }

    @Override
    public void setDimensions() {
        this.width = GameConstants.PLAYER_WIDTH; this.height = GameConstants.PLAYER_HEIGHT;
    }

    @Override
    public void update() {
        if(GamePanel.inputUp||GamePanel.inputDown) {
            if (GamePanel.inputUp) {
                speedY -= GameConstants.PLAYER_MOVESPEED;
            }
            if (GamePanel.inputDown) {
                speedY += GameConstants.PLAYER_MOVESPEED;
            }
        } else {
            if(speedY!=0){
                if(speedY>0) {
                    speedY -= GameConstants.PLAYER_STOPSPEED;
                    if (speedY < 0) {
                        speedY = 0;
                    }
                } else {
                    speedY += GameConstants.PLAYER_STOPSPEED;
                    if (speedY > 0) {
                        speedY = 0;
                    }
                }
            }
        }
        move();
    }

    private void move(){
        this.y += speedY;
        if(getBottomBound()>GameConstants.GAME_HEIGHT){
            this.y = GameConstants.GAME_HEIGHT-this.getHalfHeight();
        }
        if(getTopBound()<0){
            this.y = this.getHalfHeight();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(0,0,width,height);
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
