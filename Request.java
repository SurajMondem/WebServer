import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Request {

    private String uri;
    private String verb;
    private String httpVersion;
    private HashMap<String,String> headers;
    private static String[] validverbs = {"GET","HEAD","POST","PUT","DELETE"};

    private String RequestedString = "";

    public boolean BadRequestExceptionFlag = false;

    public Request(String test){
        RequestedString = test;
    }

    public Request(Stream client){

    }

    public void Requestparse(){
        String[] SplitLines = (RequestedString.toString().split("\\s+"));
        String[] SplitFirstline = SplitLines[0].split("\\s+");

        verb = SplitFirstline[0];
        if(SplitFirstline.length <= 3 || !isValidVerb(verb)){
            BadRequestExceptionFlag = false;
        }
        else{
            verb = SplitFirstline[0];
            uri = SplitFirstline[1];
            httpVersion = SplitFirstline[2];
            headers = new HashMap<>();

            if(SplitLines.length > 2){
                int index = 1;
                while(SplitFirstline[index].contains(": ") && index < SplitLines.length){
                    String[] TempArray = SplitLines[index].split(": ");
                    headers.put(TempArray[0], TempArray[1]);
                    index++;
                }

            }
        }

    }

    public boolean isValidVerb(String verb){
        return Arrays.asList( validverbs).contains(verb);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

}
