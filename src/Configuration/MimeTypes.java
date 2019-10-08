package Configuration;

import java.io.File;
import java.util.HashMap;

public class MimeTypes extends ConfigurationReader{

    private HashMap<String,String> types = new HashMap<>();

    public MimeTypes(String filename){
        setFile(new File (filename));
        load();
    }

    public void load(){
        super.load();
        parse();
    }

    private void parse(){
        String[] token;
        while(hasMoreLines()){
            token = nextLines().replaceAll("\\s"," ").split(" ");
            if(!token[0].startsWith("#")){
                if (token.length >= 2){
                    for(int index = 1;index < token.length;index++){
                        types.put(token[index], token[0]);
                    }
                }
            }
        }
    }

    public String lookup(String extension) {
        if(this.types.get(extension) == null){
            return "text/text" ;
        }

        return this.types.get(extension);
    }

    public HashMap<String, String> getTypes() {
        return types;
    }

    public void setTypes(HashMap<String, String> types) {
        this.types = types;
    }
}
