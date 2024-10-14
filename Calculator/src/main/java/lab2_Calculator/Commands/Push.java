package lab2_Calculator.Commands;

import java.util.Map;
import java.util.Stack;

public class Push extends Command {
    @Override
    public void exec(Stack<Double> stack, double num, String name, Map<String, Double> map) {
        if (num == 0) {
            stack.push(map.get(name));
        }
        else
            stack.push(num);
    }
}
