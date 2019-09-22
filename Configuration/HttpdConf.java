package Configuration;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class HttpdConf extends ConfigurationReader {
    private HashMap<String, String> aliases;
    private HashMap<String, String> ScriptAliases;
    private HashMap<String, String> Httpdconfig;

    public HttpdConf(String fileName) throws FileNotFoundException {
        super(fileName);
        aliases = new HashMap<>();
        ScriptAliases = new HashMap<>();
        Httpdconfig = new HashMap<>();

        load();
    }

    public void load(){

        parse();
    }

    public void parse(){

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
}
