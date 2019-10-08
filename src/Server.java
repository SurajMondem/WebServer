import Configuration.HttpdConf;
import Configuration.MimeTypes;

import java.io.*;
import java.net.*;
import java.util.*;


class Server {

    private void start() throws IOException
    {
        HttpdConf HTTPDConfiguration = new HttpdConf("httpd.conf");
        MimeTypes mimetypes = new MimeTypes("mime.types");
        Logger logger = new Logger(HTTPDConfiguration.getLogfile());
        ServerSocket serverSocket = new ServerSocket(HTTPDConfiguration.getPort());
        System.out.println("Listening for connections for port: " + HTTPDConfiguration.getPort() + "...\n");
        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection opened (" + new Date() + ")");
            new Worker(clientSocket, HTTPDConfiguration, mimetypes,logger);
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
