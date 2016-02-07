package network;

import game.GameState;

import java.io.*;
import java.net.Socket;

/**
 * ******************************
 * Project: pclient
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Connection {
    private Socket socket;
    private PrintWriter output;
    private ObjectInputStream input;
    private boolean connected = true;
    private Listener listener;
    private String id;

    public Connection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new ObjectInputStream(socket.getInputStream()) {
            };
        } catch (Exception e) {
            System.out.println("Error setting up connection to: " + ip + ":" + port);
            purge();
        }
        if (connected) {
            System.out.println("Connected to " + ip + ":" + port);
            listener = new Listener();
            listener.start();
        }
    }

    private void listenForConnectionID() {
        try {
            id = (String) input.readObject();
        } catch (Exception e) {
            System.out.println(toString() + " connection closed. Reading Connection ID aborted.");
            purge();
        }
        System.out.println("Connection ID: " + id);
    }

    private void listenForGameState() {
        try {
            System.out.println((GameState) input.readObject());
        } catch (Exception e) {
            System.out.println(toString() + " connection closed. Reading input aborted.");
            purge();
        }
    }

    public void sendToServer(String text) {
        output.printf(text + "%n%n");
    }

    public void purge() {
        try {
            connected = false;
            if (socket != null) {
                socket.close();
            }
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            System.out.println("Could not purge " + socket + ".");
        }
    }

    public boolean isConnected() {
        return connected;
    }

    private class Listener extends Thread {
        public void run() {
            listenForConnectionID();
            while (connected) {
                listenForGameState();
            }
        }
    }
}
