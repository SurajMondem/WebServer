package Exception;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

class InternalServerError extends Exception {

    private String hostName;


    public InternalServerError(Socket client) {
        super();
        try {

            PrintStream ps = new PrintStream(client.getOutputStream());
            ps.println("HTTP/1.1 500 Internal Server Error");
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
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
}
