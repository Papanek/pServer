package game.pong;

import game.engine.GameLogic;
import game.pong.state.PongGameState;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class PongLogic implements GameLogic {
    private PongGameState state;
    public PongLogic(int width, int height){
        state = new PongGameState(width,height);
    }
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void input() {

    }
}
