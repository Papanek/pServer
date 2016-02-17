package engine;

import game.GameState;
import game.pong.PongLogic;
import game.pong.state.PongGameState;

import javax.swing.*;

/**
 * ******************************
 * Project: pclient
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class GameEngine implements Runnable {
    private final int UPS = 30;
    private Thread gameLoopThread;
    private Timer timer;
    private GameLogic gameLogic;
    private GameState gameState;
    private GameWindow gameWindow;

    public GameEngine() {
        gameLoopThread = new Thread(this, "Game_loop_thread");
        gameLogic = new PongLogic();
        gameState = new PongGameState();
        gameWindow = new GameWindow(gameState);
        timer = new Timer();
    }

    public void start() {
        gameLoopThread.start();
    }

    @Override
    public void run() {
        init();
        gameLoop();
    }

    private void init() {
        timer.init();
        gameWindow.init();
        gameLogic.init(gameState);
    }

    private void gameLoop() {
        float ellapsedTime;
        float accumulator = 0f;
        float interval = 1f / UPS;
        boolean running = true;
        while (running) {
            ellapsedTime = timer.getEllapsedTime();
            accumulator += ellapsedTime;

            //TODO retrieve inputs
            gameLogic.input(gameState);

            while (accumulator >= interval) {
                gameLogic.update(gameState);
                accumulator -= interval;
            }

            gameWindow.render(accumulator/interval);
            //TODO output game state to player connections

        }
    }
}