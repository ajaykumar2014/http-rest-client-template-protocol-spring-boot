package com.http.client.rest.template.client.methods;

public class DeleteHttpRequest extends AbstractHttpRequest {
    public DeleteHttpRequest(String url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return "DELETE";
    }
}
