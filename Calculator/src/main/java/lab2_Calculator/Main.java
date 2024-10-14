package lab2_Calculator;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Factory factory = new Factory();
        Calculator calculator = new Calculator(factory);
        calculator.runFromStdin();
    }
}