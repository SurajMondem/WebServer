import Configuration.HttpdConf;
import Configuration.MimeTypes;
import Resource.Resource;
import Responses.Response;
import Exception.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Worker extends Thread{
    private Socket client;
    private MimeTypes Mimes;
    private HttpdConf config;
    private String CompleteInput = "";
    private Request request;
    private Resource resource;
    private Logger logger;


    public Worker(Socket socket,HttpdConf config, MimeTypes mimes, Logger logs) throws IOException {
        super();
        this.client = socket;
        this.config = config;
        this.Mimes = mimes;
        this.logger = logs;

        Readinput(client);
    }

    private void Readinput(Socket client) throws IOException {
        BufferedReader readClientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
        List<String> list = new ArrayList<>();
        String currentLine;
        while ((currentLine = readClientInput.readLine())!= null){
            if(currentLine.isEmpty()){
                break;
            }
            else{
                CompleteInput += currentLine;
                list.add(currentLine);
            }
        }
        System.out.println(CompleteInput);
        Stream<String> completeStream = list.stream();
        parse();
    }

    private void parse(){
        try {
            request = new Request(CompleteInput);
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
            Response response = responseFactory.getResponse(request, resource);
            response.send(client.getOutputStream());
            logger.write(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
