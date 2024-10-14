package lab2_Calculator;

import lab2_Calculator.Commands.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Factory {

    private Map<String, Class<? extends Command>> commands = new HashMap<>();

    public Factory() throws IOException, ClassNotFoundException {
        InputStream config = Factory.class.getResourceAsStream("/commands.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(config));

        String configLine;
        while ((configLine = reader.readLine()) != null) {
            String[] tokens = configLine.split("=");
            String commandName = tokens[0];
            String commandClass = tokens[1];

            Class<?> curClass = Class.forName(commandClass);
            if (Command.class.isAssignableFrom(curClass)) {
                commands.put(commandName, (Class<? extends Command>) curClass);
            }
        }
        reader.close();
    }

    public Command getCommand(String commandName) throws InstantiationException, IllegalAccessException {
        Class<? extends Command> curClass = commands.get(commandName);
        try {
            if (curClass != null) {
                return curClass.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
