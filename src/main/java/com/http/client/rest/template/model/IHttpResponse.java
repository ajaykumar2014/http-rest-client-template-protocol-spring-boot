package com.http.client.rest.template.model;

import org.apache.http.Header;

import java.io.Serializable;

public interface IHttpResponse extends Serializable {
    int statusCode();

    String getReason();

    Header[] getHeader();

    String getBody();

    String get(String key);
}
