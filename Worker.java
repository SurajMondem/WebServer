import Configuration.HttpdConf;
import Configuration.MimeTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Worker extends Thread{
    private Socket client;
    private MimeTypes Mimes;
    private HttpdConf config;
    private String CompleteInput = "";
    private String currentline = "";
    private Stream<String> CompleteStream;
    private Request request;

    public Worker(Socket socket,HttpdConf config, MimeTypes mimes) throws IOException {
        super();
        this.client = socket;
        this.config = config;
        this.Mimes = mimes;

        Readinput(client);
    }

    public void Readinput(Socket client) throws IOException {
        BufferedReader readClientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
        List<String> list = new ArrayList<>();
        while ((currentline = readClientInput.readLine())!= null){
            if(currentline.isEmpty()){
                break;
            }
            else{
                list.add(currentline);
            }
        }
        CompleteStream = list.stream();

        parse();
    }

    void parse(){
        //try {
            request = new Request(CompleteStream);
            request.Requestparse();

            if (request.BadRequestExceptionFlag) {
                //throw new BadRequest(client);
            } else {
                //run();
            }
        //} catch (BadRequest badrequest){
            //badrequest.printStacktrace();
        // }
    }

    public void run()
    {

    }

}
