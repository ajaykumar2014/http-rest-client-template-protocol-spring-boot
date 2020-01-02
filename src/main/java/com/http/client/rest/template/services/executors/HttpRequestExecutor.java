package com.http.client.rest.template.services.executors;

import com.http.client.rest.template.services.HttpClientRequestor;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;


public class HttpRequestExecutor<T> {

    private final ResponseHandler<T> responseHandler;
    private HttpUriRequest httpRequest;


    public HttpRequestExecutor(HttpUriRequest httpRequest, ResponseHandler responseHandler) {
        this.httpRequest = httpRequest;
        this.responseHandler = responseHandler;
    }

    public T execute(HttpClientRequestor httpClientRequestor) throws Exception {
        return httpClientRequestor.execute(this.httpRequest, this.responseHandler);
    }

}
