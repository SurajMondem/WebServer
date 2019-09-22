import Configuration.HttpdConf;
import Configuration.MimeTypes;

import java.net.Socket;

public class Worker extends Thread{
    private Socket client;
    private MimeTypes Mimes;
    private HttpdConf config;

    public void Worker(Socket socket,HttpdConf config, MimeTypes mimes){

    }

    public void run()
    {

    }

}
