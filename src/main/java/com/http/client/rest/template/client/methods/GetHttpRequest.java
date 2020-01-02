package com.http.client.rest.template.client.methods;

public class GetHttpRequest extends AbstractHttpRequest {

    public GetHttpRequest(String url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
