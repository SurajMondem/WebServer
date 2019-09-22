import Configuration.HttpdConf;
import Configuration.MimeTypes;
import com.sun.jdi.PathSearchingVirtualMachine;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
    private HttpdConf Configuration;
    private MimeTypes mimetypes;
    private ServerSocket socket;

    public void start() {

    }

    public static void main(String args[]){
     Server run = new Server();
     System.out.println("Server Started");
     run.start();
    }
}
