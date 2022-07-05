package sample;

import java.io.FileWriter;
import java.io.IOException;


@SaveTo(path = "file.txt")
public class TextContainer {
    public static String text = "Hello world!";

    @Saver
    public void save(String text,String path) throws IOException {

        try (FileWriter fw = new FileWriter(path, true)){
             fw.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

