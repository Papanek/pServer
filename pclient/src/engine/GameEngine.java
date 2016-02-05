package engine;

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
    private Window window;
    private Timer timer;
    public GameEngine(int width, int height){
        gameLoopThread = new Thread(this,"Game_loop_thread");
        window = new Window(width,height);
        timer = new Timer();
    }
    public void start(){
        gameLoopThread.start();
    }
    @Override
    public void run() {
        init();
        gameloop();
    }

    private void init(){
        timer.init();
        window.init();
    }

    private void gameloop(){
        float ellapsedTime;
        float accumulator = 0f;
        float interval = 1f / UPS;

        boolean running = true;
        long x;
        while (running){
            x = System.nanoTime();
            ellapsedTime = timer.getEllapsedTime();
            accumulator += ellapsedTime;
            while (accumulator >= interval){
                window.update();
                accumulator -= interval;
            }
            System.out.println(System.nanoTime()-x);
            window.render();
            sync();
        }
    }

    private void sync(){
        float loopSlot = 1f / FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while ( timer.getTime() < endTime ){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e){
            }
        }
    }

}