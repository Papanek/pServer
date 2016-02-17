package engine;

import game.Drawable;
import game.GameConstants;
import game.GameObject;

import java.awt.*;
import java.util.*;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public class GameRender {
    private float extrapolation = 0;
    public void setExtrapolation(float percent){
        extrapolation = percent;
    }

    public void render(Graphics2D g, java.util.List<GameObject> gameObjects){
        drawBackground(g);
        double x,y;
        g.setColor(Color.cyan);
        for(GameObject object : gameObjects ){
            x = object.getX()-object.getHalfWidth()+object.getSpeedX()*extrapolation;
            y = object.getY()-object.getHalfHeight()+object.getSpeedY()*extrapolation;
            g.translate(x,y);
            object.draw(g);
            g.translate(-x,-y);
        }
    }

    public void drawBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
    }

}
