package lab2_Calculator.Commands;

import java.util.Map;
import java.util.Stack;

public class Print extends Command {
    @Override
    public void exec(Stack<Double> stack, double num, String name, Map<String, Double> map) {
        if(num == 0){
            System.out.println(map.get(name));
        }
        else
            System.out.println(stack.peek());

    }
}
