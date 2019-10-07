package Responses;

import Resource.Resource;

public class ForbiddenResponse extends Response {
    public ForbiddenResponse(Resource resource) {
        super(resource);
        setCode(403);
        StatusReason = "Forbidden";
        setBodyflag(false);
    }
}
