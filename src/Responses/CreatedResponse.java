package Responses;

import Resource.Resource;

public class CreatedResponse extends Response {
    public CreatedResponse(Resource resource) {
        super(resource);
        StatusReason = "Created";
        setCode(201);
        setBodyflag(true);
    }
}
