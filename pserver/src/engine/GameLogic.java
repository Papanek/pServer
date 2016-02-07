package engine;

import game.GameState;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public interface GameLogic {
    void init();

    void update(GameState state);

    void input();
}
