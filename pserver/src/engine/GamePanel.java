package engine;

import game.GameConstants;
import game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * ******************************
 * Project: pclient
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class GamePanel extends JPanel implements KeyListener {
    private boolean inputUp = false, inputDown = false;
    private BufferedImage objectMesh;
    private GameRender gameRender;
    private GameState state;

    public GamePanel(GameState state) {
        objectMesh = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        gameRender = new GameRender();
        this.state = state;
        addKeyListener(this);
    }

    public void init() {
        this.setPreferredSize(new Dimension(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void render(float extrapolation) {
        gameRender.setExtrapolation(extrapolation);
        repaint();
    }

    @Override
    public void paint(Graphics _g) {
        Graphics2D g = objectMesh.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gameRender.render(g,state.getGameObjects());
        g.dispose();
        _g.drawImage(objectMesh, 0, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!inputUp) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                inputUp = true;
            }
        }
        if (!inputDown) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                inputDown = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            inputUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            inputDown = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
