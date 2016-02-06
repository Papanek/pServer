package game.engine;

import game.pong.PongLogic;

/**
 * ******************************
 * Project: pclient
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class GameEngine implements Runnable {
    private final int FPS = 75;
    private final int UPS = 30;
    private Thread gameLoopThread;
    private Timer timer;
    private GameLogic gameLogic;

    public GameEngine(int width, int height) {
        gameLoopThread = new Thread(this, "Game_loop_thread");
        gameLogic = new PongLogic();
        timer = new Timer();
    }

    public void start() {
        gameLoopThread.start();
    }

    @Override
    public void run() {
        init();
        gameloop();
    }

    private void init() {
        timer.init();
        gameLogic.init();
    }

    private void gameloop() {
        float ellapsedTime;
        float accumulator = 0f;
        float interval = 1f / UPS;

        boolean running = true;
        long x;
        while (running) {
            x = System.nanoTime();
            ellapsedTime = timer.getEllapsedTime();
            accumulator += ellapsedTime;

            gameLogic.input();


            while (accumulator >= interval) {


                gameLogic.update();


                accumulator -= interval;
            }

            //TODO output game state to player connections

            System.out.println(System.nanoTime() - x);
            sync();
        }
    }

    private void sync() {
        float loopSlot = 1f / FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

}