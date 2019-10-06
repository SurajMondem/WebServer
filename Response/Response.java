package Response;

import Resource.Resource;
import java.io.OutputStream;

public class Response {

    int code;
    String reasonPhrase;
    Resource resource;


    public Response(Resource resource) {
        this.resource = resource;
    }

    void send(OutputStream out){

    }
}
