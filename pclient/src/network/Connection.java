package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    private BufferedReader input;
    private boolean connected = true;
    private Listener listener;
    private int THROTTLE = 1000;

    private class Listener extends Thread{
        public void run(){
            String inputLine;
            while (connected){
                try {
                    System.out.println("Waiting to get msgs");
                    while ((inputLine = input.readLine())!=null){
                        System.out.println(inputLine);
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

    public Connection(String ip, int port){
        try{
            socket = new Socket(ip,port);
            output = new PrintWriter(socket.getOutputStream(),true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e){
            System.out.println("Error setting up connection to: "+ip+":"+port);
            purge();
        }
        if(connected) {
            System.out.println("Connected to "+ip+":"+port);
            listener = new Listener();
            listener.start();
        }
    }

    public void sendToServer(String text){
        output.printf(text+"%n%n");
    }

    public void purge(){
        try {
            connected = false;
            if(socket!=null) {
                socket.close();
            }
            if(output!=null) {
                output.close();
            }
            if(input!=null) {
                input.close();
            }
        } catch (IOException e){
            System.out.println("Could not purge "+socket+".");
        }
    }

    public boolean isConnected(){
        return connected;
    }
}
