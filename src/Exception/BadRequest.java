package Exception;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class BadRequest extends Exception{

    private String Hostname;

    public BadRequest(Socket client) {
        super();
        PrintStream ps;
        try {
            ps = new PrintStream(client.getOutputStream());
            ps.println("HTTP/1.1 400 Bad Request");
            ps.println("Date: " + new Date());
            ps.println("Server: " + ServerName());

            ps.flush();
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ServerName(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            Hostname = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return Hostname;
    }
}
