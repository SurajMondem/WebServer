package Responses;

import Resource.Resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class Response {

    private int code;
    String StatusReason;
    private boolean bodyflag;
    private Resource resource;


    Response(Resource resource) {
        this.resource = resource;
    }

    public void send(OutputStream out) {
        PrintStream printStream = new PrintStream(out);

        String httpVersion = "1.1";
        printStream.println("HTTP/" + httpVersion + " " + code + " " + StatusReason);
        printStream.println("Date: " + new Date());
        printStream.println("Server: " + ServerName());
        printStream.println("Content-Type: " + resource.getContentType());
        printStream.println("Content-Length: " + getContentLength());
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

    private String ServerName() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "apache";
    }

    public int getContentLength() {
        return resource.getBody().length;
    }

    public boolean isBodyflag() {
        return bodyflag;
    }

    void setBodyflag(boolean bodyflag) {
        this.bodyflag = bodyflag;
    }

    public int getCode() {
        return code;
    }

    void setCode(int code) {
        this.code = code;
    }

    public String getStatusReason() {
        return StatusReason;
    }

    public void setStatusReason(String statusReason) {
        StatusReason = statusReason;
    }

}
