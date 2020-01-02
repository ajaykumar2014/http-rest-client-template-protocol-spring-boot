package com.http.client.rest.template.exceptions;

import com.http.client.rest.template.model.StatusCode;

public class HttpRestTemplateException extends RuntimeException {

    private final int statusCode;
    public HttpRestTemplateException(StatusCode statusCode){
        super(statusCode.getMessage());
        this.statusCode = statusCode.getCode();
    }

    public HttpRestTemplateException(StatusCode statusCode,String message){
        super(message);
        this.statusCode = statusCode.getCode();
    }

    public HttpRestTemplateException(StatusCode statusCode,String message, Throwable throwable){
        super(message,throwable);
        this.statusCode = statusCode.getCode();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
