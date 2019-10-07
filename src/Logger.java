import Responses.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    File file;
    InetAddress inetAddress;
    String hostName;

    public Logger(String fileName) {
        file = new File(fileName);
        System.out.println(fileName);
    }

    void write (Request request, Response response){

        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file,true));

            inetAddress = InetAddress.getLocalHost();
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
            writer.print(inetAddress.getHostName() + " " + "[" + dateFormat.format(date) + "]" + " ");

            writer.print(request.getVerb()+ " " + request.getUri() + " " + request.getHttpVersion()+ " ");
            writer.print(response.getCode());
            if(response.getContentLength() > 0){
                writer.println(response.getContentLength());
            }
            else{
                writer.println("-");
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
