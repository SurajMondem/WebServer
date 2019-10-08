package Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Htaccess extends ConfigurationReader {

    private Htpassword userFile;
    private String authType;
    private String authName;
    private String require;
    private String fileLocation;

    public Htaccess(String fileName) {
        this.fileLocation = fileName;
        this.load();
    }

    public void load(){
        try {
            String fileContents = new String(Files.readAllBytes(Paths.get(fileLocation)));
            String[] SplitLine = fileContents.split("\n");
            for (String index : SplitLine){
                String[] token = SplitLine[Integer.parseInt(index)].replaceAll("\"","").split(" ", 2);
                if(token[0].equals("AuthUserFile")){
                    userFile = new Htpassword(token[1]);
                }
                else if(token[0].equals("AuthType")){
                    authType = token[1];
                }
                else if(token[0].equals("AuthName")){
                    authName = token[1];
                }
                else if(token[0].equals("Require")){
                    require = token[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuthorized(String username, String password){
        String data = username+":"+password;
        return userFile.isAuthorized(data);
    }

    public Htpassword getUserFile() {
        return userFile;
    }

    public void setUserFile(Htpassword userFile) {
        this.userFile = userFile;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }
}
