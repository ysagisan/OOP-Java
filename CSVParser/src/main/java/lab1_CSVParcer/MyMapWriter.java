package lab1_CSVParcer;

import java.io.*;
import java.util.*;

public class MyMapWriter {

    public void MapWriter(String inputFile, String outputFile, MyFileReader DataFromFile) {

        DataFromFile.InputFileReader(inputFile);
        List<Map.Entry<String, Integer>> List = new ArrayList<>(DataFromFile.getWordTable().entrySet());

        Comparator<Map.Entry<String, Integer>> ValueComp = new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
        Collections.sort(List, ValueComp);

        try(BufferedWriter OutputFile = new BufferedWriter(new FileWriter(outputFile))) {
            OutputFile.write("Слово, Частота, Частота(%)\n");
            for (Map.Entry<String, Integer> entry : List) {
                double FreqWordInPerc = ((double)entry.getValue() / DataFromFile.getCounter()) * 100.0;
                OutputFile.write(entry.getKey() + "," + entry.getValue() + "," + (int)FreqWordInPerc + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
