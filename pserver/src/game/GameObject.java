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

    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
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
