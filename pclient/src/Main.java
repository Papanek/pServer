import engine.GameEngine;
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
    public static void main(String[] args) {
        Connection connection = new Connection("192.168.182.1", 30480);
    }
}
