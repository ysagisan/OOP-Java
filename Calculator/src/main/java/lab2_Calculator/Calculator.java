package lab2_Calculator;

import lab2_Calculator.Commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private Stack<Double> stack = new Stack<>();

    private Map<String, Double> map = new HashMap<>();

    private final Factory factory;

    public Calculator(Factory factory) {
        this.factory = factory;
    }

    public void runFromStdin() throws InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        double a = 0;
        stack.push(0.0);

        while (true) {
            System.out.print("Введите команду или 'exit' для выхода: ");
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            String[] parts = line.split(" ");
            String commandName = parts[0];
            Command command;

            Arguments arguments = new Arguments();
            arguments.parseArgs(parts);

            command = factory.getCommand(commandName);

            arguments.getArgs(parts ,stack, command, map);

        }
        System.out.println("Ответ: " + stack.pop());
    }
}
