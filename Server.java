import Configuration.HttpdConf;
import Configuration.MimeTypes;
import com.sun.jdi.PathSearchingVirtualMachine;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
    private static final String CONF_FILEPATH = "Conf/httpd.conf";
    private static final String MIME_FILEPATH = "Conf/mime.types";
    private HttpdConf HTTPDConfiguration;
    private MimeTypes mimetypes;
    private ServerSocket socket;
    private Socket Connect;

    public void start() throws IOException
    {
        HTTPDConfiguration = new HttpdConf("httpd.conf");
        mimetypes = new MimeTypes("mime.types");
        System.out.println(HTTPDConfiguration.getPort());
        socket = new ServerSocket(HTTPDConfiguration.getPort());

        while(true){
            Connect = socket.accept();
            new Worker(Connect,HTTPDConfiguration,mimetypes);
            Connect.close();
        }

    }

    public static void main(String[] args) throws IOException
    {
     Server run = new Server();
     System.out.println("Server Started");
     run.start();
    }
}
