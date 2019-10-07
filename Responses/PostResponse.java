package Responses;

import Resource.Resource;

public class PostResponse extends Response {
    public PostResponse(Resource resource) {
        super(resource);
        setCode(200);
        StatusReason = "OK";
        setBodyflag(false);
    }
}
