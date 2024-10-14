package lab2_Calculator.Commands;

import java.util.Map;
import java.util.Stack;

public class Define extends Command {
    private String parameterName;
    private double parameterValue;

    public void newParametrs(String parameterName, double parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }
    @Override
    public void exec(Stack<Double> stack, double parameters, String name, Map<String, Double> map) {
        newParametrs(name, parameters);
        map.put(parameterName, parameterValue);
    }
}
