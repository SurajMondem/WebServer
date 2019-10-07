package Responses;

import Resource.Resource;

public class BadRequestResponse extends Response {
    public BadRequestResponse(Resource resource) {
        super(resource);
        setBodyflag(true);
        setCode(400);
        StatusReason = "Bad Request";
    }
}
