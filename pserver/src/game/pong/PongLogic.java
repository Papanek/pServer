package game.pong;

import game.GameState;
import engine.GameLogic;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class PongLogic implements GameLogic {
    @Override
    public void init() {

    }

    @Override
    public void update(GameState state) {
        state.update();
    }

    @Override
    public void input() {

    }
}
