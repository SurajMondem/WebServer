import Resource.Resource;
import Responses.*;
import Responses.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class ResponseFactory {

    Response getResponse(Request request, Resource resource){

        File resourcefile = new File(resource.getFinalPath());

        if(request.getVerb().equals("PUT")){
            if (resourcefile.isFile()) {
                return new BadRequestResponse(resource);
            } else {
                resource.setBody(request.getBody().getBytes());
                Path filePUT = Paths.get(resource.getFinalPath());
                try {
                    Files.write(filePUT, resource.getBody());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new CreatedResponse(resource);
            }

        }
        else if (request.getVerb().equals("GET")){

            try {
                resource.setBody(Files.readAllBytes(Paths.get(resource.getFinalPath())));
                resource.setLastModified(new Date(resourcefile.lastModified()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //resource.setModifiedResource(true);
            return new OKResponse(resource);

        }
        else if (request.getVerb().equals("DELETE")){
            if (resourcefile.isFile()) {
                resourcefile.delete();
                return new NoContentResponse(resource);
            } else {
                System.out.println("File Doesn't exist");
                return new BadRequestResponse(resource);
            }

        }
        else if (request.getVerb().equals("HEAD")){
            resource.setLastModified(new Date(resourcefile.lastModified()));
            return new HeadResponse(resource);
        }
        else if (request.getVerb().equals("POST"))
        {
            try {
                resource.setBody(Files.readAllBytes(Paths.get(resource.getFinalPath())));
            } catch (IOException e) {
                e.printStackTrace();
                return new BadRequestResponse(resource);
            }
            return new PostResponse(resource);
        }
        else {
                return new BadRequestResponse(resource);
        }

    }

}
