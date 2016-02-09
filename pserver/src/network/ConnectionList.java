package network;

import java.util.LinkedList;

/**
 * ******************************
 * Project: pserver
 * Creator: Daniel Papanek
 * Date :   2/6/2016
 * ******************************
 **/
public class ConnectionList {
    private LinkedList<Connection> connections;
    public ConnectionList(){
        connections = new LinkedList<>();
    }
    public void addConnection(Connection connection){
        connections.add(connection);
    }
    public void removeConnection(int index){
        connections.remove(index);
    }
    public Connection getConnection(int index){
        return connections.get(index);
    }
    public void disconnectAll(){
        while (connections.size()>0) {
            for (Connection c : connections) {
                c.disconnect();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
    public int size(){
        return connections.size();
    }
}
