package com.mpcs.Common;

import org.springframework.beans.factory.annotation.Required;

public class Response {

    private String jwt;

    public Response(String jwt) {
        this.jwt = jwt;
    }
    
    public String getJwt() {
        return jwt;
    }
}
