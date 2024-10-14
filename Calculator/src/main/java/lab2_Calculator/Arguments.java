package lab2_Calculator;

import lab2_Calculator.Commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Arguments {

    Map <String, Double> map = new HashMap<>();
    String value = "value";
    String nameVar = "";

    public void parseArgs(String[] s) {
        if (s.length == 2 && isNumeric(s[1])) {
            map.put(value, Double.parseDouble(s[1]));
        }
        if (s.length == 3) {
            nameVar = s[1];
            map.put(nameVar, Double.parseDouble(s[2]));
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void getArgs(String[] s, Stack stack, Command cmd, Map<String, Double> newMap) {
        if (s.length == 1) {
            cmd.exec(stack, 1, nameVar, newMap);
        }
        if (s.length == 2 && isNumeric(s[1])) {
            cmd.exec(stack, map.get(value), nameVar, newMap);
        }
        else if(s.length == 2) {
            cmd.exec(stack, 0, s[1], newMap);
        }
        if (s.length == 3) {
            cmd.exec(stack, map.get(nameVar), nameVar, newMap);
        }
    }
}
