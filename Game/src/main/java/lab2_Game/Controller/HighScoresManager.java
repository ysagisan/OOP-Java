package lab2_Game.Controller;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class HighScoresManager {
    private static final String FILENAME = "/Users/admin/IdeaProjects/Game/src/main/resources/high_scores.txt";

    private Map<String, Integer> highScores;

    public HighScoresManager() {
        highScores = new HashMap<>();
    }

    public void loadScoresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String player = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highScores.put(player, score);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveScoresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public void addScore(String playerName, int score) {
        highScores.put(playerName, score);
        loadScoresFromFile();
        highScores = getSortedHighScores();
        saveScoresToFile();
    }

    public Map<String, Integer> getSortedHighScores() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(highScores.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // Для сортировки в порядке убывания, поменяйте местами o1 и o2
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
