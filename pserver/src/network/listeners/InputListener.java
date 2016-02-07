package network.listeners;

import network.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class InputListener extends Thread {
    private Connection connection;
    private BufferedReader input;
    private Socket socket;
    public InputListener(Connection connection, Socket socket) {
        this.socket = socket;
        this.connection = connection;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Error setting up input/output");
        }
    }

    public void run() {
        String inputLine;
        while (connection.isConnected()) {
            try {
                while ((inputLine = input.readLine()) != null) {
                    System.out.println(inputLine);
                }
            } catch (IOException e) {
                System.out.println(toString() + " input interrupted.");
                connection.disconnect();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(toString() + " thread interrupted");
                connection.disconnect();
            }
        }
    }
    public void close(){
        try {
            socket.close();
            input.close();
            System.out.println("Connection closed");
        } catch (IOException e){}
    }
}
