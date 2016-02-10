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
    void setDimensions(int width, int height);
    default double getTopBound() {
        return getY()-(getHeight()/2);
    }
    default double getBottomBound() {
        return getY()+(getHeight()/2);
    }
    default double getRightBound() {
        return getX()+(getWidth()/2);
    }
    default double getLeftBound() {
        return getY()-(getWidth()/2);
    }
}
