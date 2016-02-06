package network;

import network.listeners.InputListener;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Connection implements IBroadcast {
    private PrintWriter output;
    private boolean connected = true;
    private InputListener inputListener;
    private int THROTTLE = 1000;

    public Connection(Socket socket) {
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            System.out.println("Error setting up input/output");
            disconnect();
        }
        if (connected) {
            inputListener = new InputListener(this, socket);
            inputListener.start();
        }
    }

    @Override
    public void broadcast(String text) {
        Connection connection;
        for (int i = 0; i < Server.connections.size(); i++) {
            connection = Server.connections.get(i);
            if (!connection.equals(this)) {
                connection.sendMessage(text);
            }
        }
    }

    @Override
    public void sendMessage(String text) {
        output.println(text);
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
}
