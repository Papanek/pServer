package engine;

import game.GameState;
import game.pong.PongLogic;
import game.pong.state.PongGameState;

import javax.swing.*;
import java.awt.*;

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

    JFrame frame = new JFrame();
    Panel panel;

    public GameEngine() {
        gameLoopThread = new Thread(this, "Game_loop_thread");
        gameLogic = new PongLogic();
        gameState = new PongGameState();
        timer = new Timer();
        panel = new Panel(gameState);
        panel.setPreferredSize(new Dimension(800,600));
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        gameLogic.init();
    }

    private void gameLoop() {
        float ellapsedTime;
        float accumulator = 0f;
        float interval = 1f / UPS;
        boolean running = true;
        while (running) {
            ellapsedTime = timer.getEllapsedTime();
            accumulator += ellapsedTime;

            gameLogic.input();

            while (accumulator >= interval) {
                gameLogic.update(gameState);
                accumulator -= interval;
            }

            panel.render(gameState,accumulator/interval);
            //TODO output game state to player connections

        }
    }

}