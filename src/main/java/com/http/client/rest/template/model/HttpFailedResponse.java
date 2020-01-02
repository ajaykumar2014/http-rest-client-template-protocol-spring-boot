package com.http.client.rest.template.model;

import org.apache.http.Header;
import org.apache.http.StatusLine;

public class HttpFailedResponse implements IHttpResponse {
    private int statusCode;
    private String reason;

    public HttpFailedResponse(int statusCode, String reason) {
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public HttpFailedResponse(StatusLine statusLine) {
        this.statusCode = statusLine.getStatusCode();
        this.reason = statusLine.getReasonPhrase();
    }


    @Override
    public int statusCode() {
        return this.statusCode;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public Header[] getHeader() {
        return null;
    }


    @Override
    public String getBody() {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }


}
