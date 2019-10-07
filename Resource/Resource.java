package Resource;

import Configuration.HttpdConf;
import Configuration.MimeTypes;

import java.util.Date;

public class Resource {

    private String queryString;
    private String FinalPath;
    private HttpdConf config;
    private MimeTypes mimeTypes;
    private String uri;
    private String contentType;
    private String rootpath;
    private String Temppath = "";
    private boolean URIModified = false;
    private boolean resourceModified = false;
    private Date lastModified;
    private byte[] body = "".getBytes();

    public Resource(String uri, HttpdConf config, MimeTypes mimeTypes){
        this.config = config;
        this.mimeTypes = mimeTypes;
        this.uri = uri;

        this.contentType = setContentType(uri,mimeTypes);
        rootpath = config.getDocumentRoot();

        if (URIModified == false) {
           this.Temppath  = rootpath + this.uri;
        }

        FinalPath = Temppath;
        setQueryString();

        if (!Temppath.contains(".") && !isScript()){
            FinalPath = FinalPath + "/" + config.getDirectoryIndex();
        }
    }

    public String setContentType(String uri, MimeTypes mimeTypes) {

        String[] temp = uri.split("/");

        if(temp.length <= 1){
            return mimeTypes.lookup("html");
        }
        else if(temp.length > 1){
            String[] extension = temp[temp.length-1].split("\\.");
            if(extension.length>1){
                return mimeTypes.lookup(extension[extension.length-1]);
            }
        }
        return "text/text";

    }

    public void setQueryString() {
        if (isScript()) {
            if (FinalPath.contains("?") && !FinalPath.substring(FinalPath.length() - 1).equals("?")) {
                String separateString[] = FinalPath.split("\\?+");
                FinalPath = separateString[0];
                queryString = separateString[1];
            }
        }
    }

    public String absolutePath(){

        return null;
    }

    boolean isScript(){
        return false;
    }

    boolean isProtected(){
        return false;
    }

    public HttpdConf getConfig() {
        return config;
    }

    public void setConfig(HttpdConf config) {
        this.config = config;
    }

    public MimeTypes getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(MimeTypes mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContentType() {
        return contentType;
    }

    public String getRootpath() {
        return rootpath;
    }

    public void setRootpath(String rootpath) {
        this.rootpath = rootpath;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public boolean isResourceModified() {
        return resourceModified;
    }

    public void setResourceModified(boolean resourceModified) {
        this.resourceModified = resourceModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getFinalPath() {
        return FinalPath;
    }

    public void setFinalPath(String finalPath) {
        FinalPath = finalPath;
    }
    public boolean isURIModified() {
        return URIModified;
    }

    public void setURIModified(boolean URIModified) {
        this.URIModified = URIModified;
    }

}
