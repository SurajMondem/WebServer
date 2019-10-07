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

    @Override
    public void send(OutputStream out) {
        PrintStream printStream = new PrintStream(out);

        printStream.println("HTTP/" + httpVersion + " " + code + " " + StatusReason);
        printStream.println("Date: " + new Date());
        printStream.println("Server: " + ServerName());
        //printStream.println("Content-Type: " + resource.getContentType());
        //printStream.println("Content-Length: " +  resource.getBody().length);
        //printStream.println("Last-Modified: " + resource.getLastModified());
        printStream.println();

        printStream.flush();
        printStream.close();
        try {
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
