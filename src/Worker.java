import Configuration.HttpdConf;
import Configuration.MimeTypes;
import Resource.Resource;
import Responses.Response;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Worker extends Thread{
    private Socket client;
    private MimeTypes Mimes;
    private HttpdConf config;
    private String CompleteInput = "";
    private String currentline = "";
    private Stream<String> CompleteStream;
    private Request request;
    private Resource resource;
    private Response response;

    static final File WEB_ROOT = new File("");
    static final String DEFAULT_FILE = "index.html";
    static final String FILE_NOT_FOUND = "404.html";
    static final String METHOD_NOT_SUPPORTED = "not_supported.html";


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
        //System.out.println(readClientInput.readLine());
        //System.out.println((currentline = readClientInput.readLine())!= null);
        while ((currentline = readClientInput.readLine())!= null){
            if(currentline.isEmpty()){
                break;
            }
            else{
                CompleteInput += currentline;
                //System.out.println(currentline);
                list.add(currentline);
            }
        }
        System.out.println(CompleteInput);
        CompleteStream = list.stream();
        parse();
    }

    void parse(){
        try {
            request = new Request(CompleteInput);
            //request = new Request(CompleteStream);
            request.Requestparse();
            if (request.BadRequestExceptionFlag) {
                throw new BadRequest(client);
            } else {
                run();
            }
        } catch (BadRequest badrequest){
            badrequest.printStackTrace();
        }
    }

    public void run()
    {
        Resource resource = new Resource(request.getUri(), config, Mimes);

        ResponseFactory responseFactory = new ResponseFactory();

        try {
            response = responseFactory.getResponse(request, resource);
            response.send(client.getOutputStream());
            //logger.write(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
