package game;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public abstract class GameObject implements Graphable, Drawable, Updatable, java.io.Serializable {

    protected double x,y;
    protected int width, height;
    protected double speedX, speedY;

    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
        width = 0;
        height = 0;
        speedX = 0;
        speedY = 0;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
