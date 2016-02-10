package game.pong;

import engine.EngineConstants;
import engine.GameLogic;
import game.GameState;
import game.pong.state.PongBall;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class PongLogic implements GameLogic {
    @Override
    public void init(GameState state) {

    }

    @Override
    public void update(GameState state) {
        state.update();
    }

    @Override
    public void input(GameState state) {

    }
}
