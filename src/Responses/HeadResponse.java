package Responses;



import Resource.Resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

public class HeadResponse extends Response {
    public HeadResponse(Resource resource) {
        super(resource);
        setBodyflag(false);
        setCode(200);
    }
}
