package lab1_CSVParcer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MyFileReader {

    private HashMap<String, Integer> WordTable = new HashMap<String, Integer>();
    private int Counter;

    public MyFileReader() {
        this.Counter = 0;
        this.WordTable = new HashMap<>();
    }

    public void InputFileReader(String inputFile) {
        try (BufferedReader InputFile = new BufferedReader(new FileReader(inputFile))) {
            String FirstLine;
            StringBuilder CleanedWord = new StringBuilder();

            while ((FirstLine = InputFile.readLine()) != null) {
                for (int i = 0; i < FirstLine.length(); i++) {
                    if (Character.isLetterOrDigit(FirstLine.charAt(i))) {
                        CleanedWord.append(FirstLine.charAt(i));
                    } else {
                        this.WordTable.put(CleanedWord.toString(), WordTable.getOrDefault(CleanedWord.toString(), 0) + 1);
                        CleanedWord = new StringBuilder();
                        this.Counter++;
                    }
                }
                this.WordTable.put(CleanedWord.toString(), WordTable.getOrDefault(CleanedWord.toString(), 0) + 1);
                CleanedWord = new StringBuilder();
                this.Counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, Integer> getWordTable() {
        return WordTable;
    }

    public int getCounter() {
        return Counter;
    }
}


