package lab1_CSVParcer;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyFileReader DataFile = new MyFileReader();
        MyMapWriter Writer = new MyMapWriter();
        Writer.MapWriter(args[0], args[1], DataFile);
    }
}
