package engine;

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
public class Panel extends JPanel implements KeyListener{
        private boolean inputUp = false, inputDown = false;
        private int width, height;
        int x = 10;
        public Panel(int width, int height){
            this.width = width;
            this.height = height;
            addKeyListener(this);
        }

        public void init(){
            this.setPreferredSize(new Dimension(width,height));
            this.setFocusable(true);
            this.requestFocusInWindow();
            this.setBackground(Color.black);
        }

        public void update(){
            if(inputUp){
                x++;
            }
            if(inputDown){
                x--;
            }
        }

        public void render(){
            repaint();
        }

        @Override
        public void paint(Graphics g){
            g.setColor(Color.WHITE);
            g.fillRect(0,0,width,height);
            g.setColor(Color.BLACK);
            g.drawOval(x,x,x,x);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(!inputUp) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    inputUp = true;
                }
            }
            if(!inputDown) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    inputDown = true;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_UP){
                inputUp = false;
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN){
                inputDown = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }
