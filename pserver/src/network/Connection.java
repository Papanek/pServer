package network;

import network.listeners.InputListener;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Connection {
    private ObjectOutputStream output;
    private boolean connected = true;
    private InputListener inputListener;
    private static int idSequence = 5000;
    private String id;

    public Connection(Socket socket) {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            inputListener = new InputListener(this, socket);
            inputListener.start();
            id = ""+idSequence++;
            System.out.println( "Connection Established. "+socket+" User ID: "+id);
        } catch (Exception e) {
            System.out.println("Error setting up input/output");
            disconnect();
        }
        sendUserID();
    }

    public void sendGameState(GameState state) {
        try {
            output.writeObject(state);
        } catch (IOException e){

        }
    }

    private void sendUserID(){
        try {
            output.writeObject(new String(id));
        } catch (IOException e){

        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void purge() {
        inputListener.close();
    }

    public void disconnect(){
        connected = false;
    }

    public String getId(){
        return id;
    }
}
