package network;

import engine.GameEngine;
import network.listeners.ConnectionListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Server extends Thread {
    private InetAddress hostAddress;
    private final int PORT = 30480;

    private static Server theServer = new Server();
    private ConnectionList connections;

    private ConnectionListener connectionListener;
    private boolean running = true;

    public static synchronized Server getInstance() {
        return theServer;
    }

    private Server() {
        connections = new ConnectionList();
        try {
            hostAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            connectionListener = new ConnectionListener(this, new ServerSocket(PORT, 0, hostAddress));
            System.out.println("Server socket created.");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        connectionListener.start();
        this.start();
    }

    public void run() {
        System.out.println("Pong Started!");
        GameEngine engine = null;
        while (running){
            System.out.println(clientsConnected());
            if(clientsConnected()==2&&engine==null){
                engine = new GameEngine();
                engine.start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){}
        }
        shutdown();
    }

    private void shutdown(){
        connections.disconnectAll();
        running = false;
        connectionListener.interrupt();
        System.out.println("Server Shutdown");
    }

    public ConnectionList getConnections(){
        return connections;
    }

    public boolean isRunning() {
        return running;
    }

    public int clientsConnected(){
        return connections.size();
    }
}
