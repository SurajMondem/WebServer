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
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start() throws IOException
    {
        HTTPDConfiguration = new HttpdConf("httpd.conf");
        mimetypes = new MimeTypes("mime.types");
        Logger logger = new Logger(HTTPDConfiguration.getLogfile());
        serverSocket = new ServerSocket(HTTPDConfiguration.getPort());
        System.out.println("Listening for connections for port: " + HTTPDConfiguration.getPort() + "...\n");
        while(true){
            clientSocket = serverSocket.accept();
            System.out.println("Connection opened (" + new Date() + ")");
            new Worker(clientSocket,HTTPDConfiguration,mimetypes,logger);
            clientSocket.close();
        }

    }

    public static void main(String[] args) throws IOException
    {
     Server run = new Server();
     System.out.println("Server Started");
     run.start();
    }
}
