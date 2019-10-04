package Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class HttpdConf extends ConfigurationReader {
    private HashMap<String, String> aliases = new HashMap<>();
    private HashMap<String, String> ScriptAliases = new HashMap<>();
    private HashMap<String, String> Httpdconfig = new HashMap<>();
    private int port = 8080;
    private String ServerRoot = "";
    private String DocumentRoot = "";
    private String AccessFile = ".htaccess";
    private String directoryIndex = "index.html";

    public HttpdConf(String fileName){
        setFile(new File(fileName));
        System.out.println("Reached:");
        load();
    }

    public void load(){
        super.load();
        System.out.println("Reached:");
        parse();
    }

    public void parse() {
        String[] token;
        while (hasMoreLines()){
            token = nextLines().split(" ");
            if(token.length > 1){
                token[1] = token[1].replaceAll("\"","");
                System.out.println("Print Statement :" + token[0] + " " + token[1] + " ");
                if(token[0].equals("DocumentRoot")) {

                    DocumentRoot = token[1];
                }
                else if(token[0].equals("Alias")) {
                    aliases.put(token[1], token[2]);
                }
                else if(token[0].equals("ScriptAlias")) {
                    ScriptAliases.put(token[1], token[2]);
                }
                else if(token[0].equals("Listen")) {
                        port = Integer.parseInt(token[1].trim());
                }
                else if (token[0].equals("ServerRoot")) {
                        ServerRoot = token[1];
                }
                else if(token[0].equals("AccessFilename")) {
                    AccessFile = token[1];
                }
                else if(token[0].equals("DirectoryIndex")) {
                        directoryIndex = token[1];
                }
                else{
                            Httpdconfig.put(token[0],token[1]);
                }
            }
        }
    }

    public HashMap<String, String> getAliases() {
        return aliases;
    }

    public HashMap<String, String> getScriptAliases() {
        return ScriptAliases;
    }

    public HashMap<String, String> getHttpdconfig() {
        return Httpdconfig;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerRoot() {
        return ServerRoot;
    }

    public void setServerRoot(String serverRoot) {
        ServerRoot = serverRoot;
    }

    public String getDocumentRoot() {
        return DocumentRoot;
    }

    public void setDocumentRoot(String documentRoot) {
        DocumentRoot = documentRoot;
    }

    public String getAccessFile() {
        return AccessFile;
    }

    public void setAccessFile(String accessFile) {
        AccessFile = accessFile;
    }

    public String getDirectoryIndex() {
        return directoryIndex;
    }

    public void setDirectoryIndex(String directoryIndex) {
        this.directoryIndex = directoryIndex;
    }


}
