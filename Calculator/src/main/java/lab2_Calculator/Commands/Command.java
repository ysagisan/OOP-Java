package lab2_Calculator.Commands;

import java.util.Map;
import java.util.Stack;

public abstract class Command {
    public abstract void exec(Stack<Double> stack, double num, String name, Map<String, Double> map);
}