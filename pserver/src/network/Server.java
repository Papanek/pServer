package network;

import network.listeners.ConnectionListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Server extends Thread {
    public static LinkedList<Connection> connections;
    private final int PORT = 30480;
    private InetAddress hostAddress;
    private ConnectionListener connectionListener;
    private boolean running = true;

    public Server() {
        connections = new LinkedList<>();
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
    }

    public void run() {
        System.out.println("Pong Server Started!");
        System.out.println("q to shutdown server");
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("q")){
            command = in.nextLine();
            System.out.print(command);
        }
        shutdown();
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public boolean isRunning() {
        return running;
    }

    private void shutdown(){
        while(connections.size()>0){
            for(Connection c : connections){
                c.disconnect();
            }
            try{
                Thread.sleep(100);
            } catch (InterruptedException e){

            }
        }
        running = false;
        connectionListener.interrupt();
        System.out.println("Server Shutdown");
    }
}
