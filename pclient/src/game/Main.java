package game;

import network.Connection;

import java.util.Scanner;

/**
 * ******************************
 * Project: pclient
 * Creator: Daniel Papanek
 * Date :   2/4/2016
 * ******************************
 **/
public class Main {
    public static void main(String[] args){
        Connection connection = new Connection("192.168.182.1", 30480);
        try {
            if(connection.isConnected()) {
                Scanner in = new Scanner(System.in);
                System.out.println("Welcome to the terminus.");
                System.out.print("Screen name: ");
                String name = in.nextLine() + " > ";
                System.out.println("You can now talk to the terminal.");
                System.out.println("'q' to exit");
                System.out.print(name);
                String text = in.nextLine();
                while (!text.equals("q")&&connection.isConnected()) {
                    connection.sendToServer(name + text);
                    System.out.print(name);
                    text = in.nextLine();
                }
            }
        } finally {
            connection.purge();
        }
    }
}
