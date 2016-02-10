package game;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public interface Graphable {
    double getX();
    double getY();
    int getWidth();
    int getHeight();
    void setDimensions();
    default double getTopBound() {
        return getY()-getHalfHeight();
    }
    default double getBottomBound() { return getY()+getHalfHeight(); }
    default double getRightBound() { return getX()+getHalfWidth(); }
    default double getLeftBound() {
        return getX()-getHalfWidth();
    }
    default double getHalfWidth() { return getWidth()/2; }
    default double getHalfHeight() { return getHeight()/2; }
}