package Resource;

import Configuration.HttpdConf;

public class Resource {

    Resource(String uri, HttpdConf config){

    }

    String absolutePath(){

        return null;
    }

    boolean isScript(){
        return false;
    }

    boolean isProtected(){
        return false;
    }

}
