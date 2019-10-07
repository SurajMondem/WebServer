package Responses;

import Resource.Resource;

public class NoContentResponse extends Response {
    public NoContentResponse(Resource resource) {
        super(resource);
        setCode(204);
        StatusReason = "No Content";
        setBodyflag(true);
    }
}
