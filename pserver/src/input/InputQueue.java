package input;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Daniel on 2/26/2016.
 */
public class InputQueue implements Serializable {
    private Queue<InputCommand> commands;
    public InputQueue(){
        commands = new LinkedList<>();
    }
    public InputCommand pop(){
        return commands.poll();
    }
    public void push(InputCommand command){
        commands.add(command);
    }
}