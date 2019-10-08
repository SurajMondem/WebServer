package Responses;

import Resource.Resource;

class ForbiddenResponse extends Response {
    public ForbiddenResponse(Resource resource) {
        super(resource);
        setCode(403);
        StatusReason = "Forbidden";
        setBodyflag(false);
    }
}
