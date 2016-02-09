package engine;

import sun.util.locale.provider.JRELocaleConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

/**
 * ******************************
 * Project: pclient
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Window extends JFrame {
    private Panel panel;

    public Window(int width, int height) {
        panel = new Panel(width, height);
    }

    public void init() {
        panel.init();
        this.add(panel);
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void update() {
        panel.update();
    }

    public void render() {
        panel.render();
    }
}
