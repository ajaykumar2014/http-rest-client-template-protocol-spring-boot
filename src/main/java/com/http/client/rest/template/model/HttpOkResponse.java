package com.http.client.rest.template.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.http.client.rest.template.helper.JSONUtils;
import org.apache.http.Header;

public class HttpOkResponse implements IHttpResponse {

    private int statusCode;
    private String response;

    private ObjectNode objectNode;

    public HttpOkResponse(int statusCode, String response) {
        this.statusCode = statusCode;
        this.response = response;
    }


    public HttpOkResponse(int statusCode, ArrayNode data) {
        this.statusCode = statusCode;
        this.response = data == null ? "" : JSONUtils.toString(data);
    }

    public HttpOkResponse(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.response = data == null ? "" : JSONUtils.toString(data);
    }

    @Override
    public int statusCode() {
        return this.statusCode;
    }

    @Override
    public String getReason() {
        return null;
    }

    @Override
    public Header[] getHeader() {
        return null;
    }

    @Override
    public String getBody() {
        return this.response;
    }

    @Override
    public String get(String key) {
        return objectNode.get(key) == null ? null : objectNode.get(key).asText();
    }

}
