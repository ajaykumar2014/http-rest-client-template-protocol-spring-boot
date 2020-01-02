package com.http.client.rest.template.client.methods;

public class PostHttpRequest extends AbstractHttpRequest {

    public PostHttpRequest(String url, String body)  {
        super(url, body);
    }

    @Override
    public String getMethod() {
        return "POST";
    }
}
