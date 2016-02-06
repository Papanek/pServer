package network.listeners;

import network.Connection;
import network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/5/2016
 * ******************************
 **/
public class ConnectionListener extends Thread {
    private Server server;
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private ConnectionChecker checker;

    public ConnectionListener(Server server, ServerSocket serverSocket) {
        this.server = server;
        this.serverSocket = serverSocket;
        checker = new ConnectionChecker();
        checker.start();
    }

    private class ConnectionChecker extends Thread{
        @Override
        public void run() {
            super.run();
            while (server.isRunning()){
                checkClosedConnections();
                sleepThread(1);
            }
        }
        private void checkClosedConnections() {
            Connection connection;
            for (int i = 0; i < server.connections.size(); i++) {
                connection = server.connections.get(i);
                if (!connection.isConnected()) {
                    System.out.println(connection + " removed due to lack of connection.");
                    connection.purge();
                    server.connections.remove(i);
                }
            }
        }
    }

    @Override
    public void run() {
        while (server.isRunning()) {
            if (server.connections.size() <= 1) {
                System.out.println("Listening for a new connection");
                waitForConnection();
                sleepThread(2.5);
            } else {
                System.out.println("Not listening for new connections two players connected.");
                sleepThread(5);
            }
        }
    }

    private void waitForConnection(){
        try {
            connectionSocket = serverSocket.accept();
            server.addConnection(new Connection(connectionSocket));
            System.out.println("Connection " + connectionSocket + " established.");
        } catch (IOException e) {
            System.out.println("Connection Interrupted");
        }
    }

    private void sleepThread(double seconds){
        try {
            Thread.sleep((int)seconds*1000);
        } catch (InterruptedException e) {
            System.out.println("Connection Listener Interrupted");
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try{
            serverSocket.close();
        } catch (IOException e){

        }
    }
}
