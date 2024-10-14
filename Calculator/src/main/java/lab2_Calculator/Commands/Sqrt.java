package lab2_Calculator.Commands;

import java.util.Map;
import java.util.Stack;

public class Sqrt extends Command{
    @Override
    public void exec(Stack<Double> stack, double num, String name, Map<String, Double> map) {
        if (num == 0) {
            stack.push(Math.sqrt(map.get(name)));
        }
        else
            stack.push(Math.sqrt(num));
    }
}
