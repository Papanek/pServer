package game;

import network.IBroadcast;
import network.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Player implements IBroadcast {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private boolean connected = true;
    private Listener listener;
    private int THROTTLE = 1000;

    public Player(Socket socket){
        this.socket = socket;
        try {
            output = new PrintWriter(socket.getOutputStream(),true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e){
            System.out.println("Error setting up input/output");
            purge();
        }
        if(connected) {
            listener = new Listener();
            listener.start();
        }
    }

    private class Listener extends Thread{
        public void run(){
            String inputLine;
            String log;
            while (connected){
                try {
                    while ((inputLine = input.readLine())!=null){
                        System.out.println(inputLine);
                        broadcast(inputLine);
                    }
                } catch (IOException e){
                    System.out.println(toString()+ " connection closed. Reading input aborted.");
                    purge();
                }
                try {
                    Thread.sleep(THROTTLE);
                } catch (InterruptedException e){
                    System.out.println(toString()+" connection closed. Thread aborted");
                    purge();
                }

            }
        }
    }

    @Override
    public void broadcast(String text) {
        Player player;
        for(int i = 0; i< Server.players.size(); i++){
            player = Server.players.get(i);
            if(!player.equals(this)) {
                player.sendMessage(text);
            }
        }
    }

    @Override
    public void sendMessage(String text) {
        output.println(text);
    }

    public boolean isConnected(){
        return connected;
    }

    public void purge(){
        try {
            connected = false;
            socket.close();
        } catch (IOException e){
            System.out.println("Could not purge "+socket+".");
        }
    }

    @Override
    public String toString(){
        return socket.toString();
    }

}
