package engine;

import game.GameState;

import javax.swing.*;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/9/2016
 * ******************************
 **/
public class GameWindow extends JFrame {
    private GamePanel gamePanel;

    public GameWindow(GameState state) {
        gamePanel = new GamePanel(state);
    }

    public void init() {
        gamePanel.init();
        this.add(gamePanel);
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void render(float extrapolation) {
        gamePanel.render(extrapolation);
    }
}
