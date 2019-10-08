package Responses;



import Resource.Resource;

public class HeadResponse extends Response {
    public HeadResponse(Resource resource) {
        super(resource);
        setBodyflag(false);
        setCode(200);
    }
}
