package game;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public abstract class GameObject implements Locatable, Drawable, Updatable, Sizable, java.io.Serializable {

    protected double x,y;

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
}
