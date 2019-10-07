package Responses;

import Resource.Resource;

public class NotFoundResponse extends Response {
    public NotFoundResponse(Resource resource) {
        super(resource);
        setBodyflag(false);
        setCode(404);
        StatusReason = "Not Found";
    }
}
