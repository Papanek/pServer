package network;

import game.Player;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Server {
    private final int PORT = 30480;
    private ServerSocket serverSocket;
    private InetAddress hostAddress;
    private Socket socket;
    public static LinkedList<Player> players;
    public Server(){
        players = new LinkedList<>();
        try {
            hostAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e){
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            serverSocket = new ServerSocket(PORT,0,hostAddress);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Socket "+serverSocket+" created.");
    }

    public void run(){
        System.out.println("Pong Server Started!");
        Player player;
        while(true){
            for(int i = 0; i< players.size(); i++){
                player = players.get(i);
                if(!player.isConnected()){
                    System.out.println(player +" removed due to lack of connection.");
                    player.purge();
                    players.remove(i);
                }
            }
            System.out.println("Listening for new connections..");
            try{
                socket = serverSocket.accept();
            } catch (IOException e){
                System.out.println("No user connected");
            }
            players.add(new Player(socket));
            System.out.print("Player "+socket+" added.");
        }
    }
}
