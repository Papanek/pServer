package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Player {
    private final int THROTTLE = 1000;
    private Socket socket;
    private boolean connected;
    private Input input;

    public Player(Socket socket){
        this.socket = socket;
        connected = true;
        input = new Input();
        input.start();
    }

    private class Input extends Thread{
        //private ObjectInputStream in;
        private BufferedReader reader;
        public void run(){
            /*try {
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e){
                System.out.println("Could not get connection from "+toString());
                return;
            }*/

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e){
                System.out.println(toString()+" could not setup input stream. Disconnecting.");
                purge();
            }

            System.out.println(socket+" has connected input.");
            String inputLine;
            while (connected){
                try {
                    while ((inputLine = reader.readLine())!=null){
                        System.out.println(inputLine);
                    }
                } catch (IOException e){
                    System.out.println(toString()+ " input interrupted.");
                }
                try {
                    Thread.sleep(THROTTLE);
                } catch (InterruptedException e){
                    System.out.println(toString()+" has input interrupted.");
                }
            }
        }
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
