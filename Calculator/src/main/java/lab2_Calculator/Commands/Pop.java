package lab2_Calculator.Commands;

import java.util.Map;
import java.util.Stack;

public class Pop extends Command {
    @Override
    public void exec(Stack<Double> stack, double num, String name, Map<String, Double> map) {
        stack.pop();
    }
}
