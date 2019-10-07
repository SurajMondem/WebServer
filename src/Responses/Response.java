package Responses;

import Resource.Resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class Response {

    protected int code;
    protected String StatusReason;
    private Resource resource;
    protected InetAddress inetAddress;
    String hostName;
    String httpVersion = "1.1";
    boolean bodyflag;


    public Response(Resource resource) {
        this.resource = resource;
    }

    public void send(OutputStream out){
        PrintStream printStream = new PrintStream(out);

        printStream.println("HTTP/" + httpVersion + " " + code + " " + StatusReason);
        printStream.println("Date: " + new Date());
        printStream.println("Server: " + ServerName());
        printStream.println("Content-Type: " + resource.getContentType());
        printStream.println("Content-Length: " +  getContentLength());
        printStream.println();

        if (bodyflag && !resource.getContentType().contains("image")) {
            printStream.println(new String(resource.getBody()));
        } else if (bodyflag && resource.getContentType().contains("image")) {
            try {
                out.write(resource.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printStream.flush();
        printStream.close();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ServerName(){
        try {
            inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
            return hostName;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "apache";
    }

    public int getContentLength(){
        return resource.getBody().length;
    }

    public void setBodyflag(boolean bodyflag) {
        this.bodyflag = bodyflag;
    }

    public boolean isBodyflag() {
        return bodyflag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatusReason() {
        return StatusReason;
    }

    public void setStatusReason(String statusReason) {
        StatusReason = statusReason;
    }

}
