package Responses;

import Resource.Resource;

public class OKResponse extends Response {
    public OKResponse(Resource resource) {
        super(resource);
        setCode(200);
        setBodyflag(true);
        StatusReason = "OK";
    }
}
