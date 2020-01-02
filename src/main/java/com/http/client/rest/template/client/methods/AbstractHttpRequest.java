package com.http.client.rest.template.client.methods;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.net.URI;

public abstract class AbstractHttpRequest extends HttpEntityEnclosingRequestBase {


    protected AbstractHttpRequest(String url) {
        super.setURI(URI.create(url));
    }

    protected AbstractHttpRequest(String url, String body) {
        this(url);
        this.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
    }

    public abstract String getMethod();

    public void addHeader(String name, String value) {
        super.addHeader(name, value);
    }


}
