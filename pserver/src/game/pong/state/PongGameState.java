package game.pong.state;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class PongGameState {
    private PongPlayer player1,player2;
    private PongBall ball;
    public PongGameState(int width, int height){
        player1 = new PongPlayer("Player 1",10,10);
        player2 = new PongPlayer("Player 2",width-10-player2.getWidth(),10);
        ball    = new PongBall(width/2,height/2);
    }
}
