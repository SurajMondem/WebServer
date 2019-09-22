package Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigurationReader {
    private File file;
    private BufferedReader read;
    private String Contents;
    private String[] parsedFile;
    private int index = 0;

    public ConfigurationReader(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        load();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        this.Contents = contents;
    }

    public String[] getParsedfile() {
        return parsedFile;
    }

    public void setParsedFile(String[] parsedfile) {
        this.parsedFile = parsedfile;
    }

    public boolean hasMoreLines() throws IOException {
        if(parsedFile.length > index) {
            return true;
        }
        else {
            return false;
        }
    }

    public String nextLines() throws IOException {
        if(hasMoreLines() == true){
            String nextline = parsedFile[index];
            index++;
            return nextline;
        }
        return null;
    }

    public void load(){
        try {
            Contents = new String(Files.readAllBytes(Paths.get(""+file)));
            parse(Contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(String filename){
        parsedFile = filename.split("\n");
    }



}
