package Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class ConfigurationReader {
    private File file;
    private String[] parsedFile;
    private int index = 0;

    ConfigurationReader(String fileName) {
        file = new File(fileName);
        load();
    }

    ConfigurationReader() {
    }

    void setFile(File file) {
        this.file = file;
    }

    boolean hasMoreLines() {
        return parsedFile.length > index;
    }

    String nextLines() {
        if (hasMoreLines()) {
            String nextLine = parsedFile[index];
            index++;
            return nextLine;
        }
        return null;
    }

    void load() {
        try {
            String contents = new String(Files.readAllBytes(Paths.get("src/conf/" + file)));
            parse(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(String filename) {
        parsedFile = filename.split("\n");
    }


}
