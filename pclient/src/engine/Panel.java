package engine;

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
public class Panel extends JPanel implements KeyListener {
    float extrapolation;
    BufferedImage objectMesh;
    private boolean inputUp = false, inputDown = false;
    private int width, height;
    private GameState state;

    public Panel(int width, int height, GameState state) {
        this.width = width;
        this.height = height;
        objectMesh = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        this.state = state;
        addKeyListener(this);
    }

    public void init() {
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setBackground(Color.black);
    }

    public void update() {

    }

    public void render(GameState state, float extrapolation)
    {
        this.state = state;
        this.extrapolation = extrapolation;
        repaint();
    }

    @Override
    public void paint(Graphics _g) {
        Graphics2D g = objectMesh.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.BLACK);
        state.draw(g, extrapolation);

        _g.drawImage(objectMesh, 0, 0, null);
        g.dispose();
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
