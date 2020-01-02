package com.http.client.rest.template.client;

import com.http.client.rest.template.client.methods.DeleteHttpRequest;
import com.http.client.rest.template.client.methods.GetHttpRequest;
import com.http.client.rest.template.client.methods.PostHttpRequest;
import com.http.client.rest.template.handlers.DefaultResponseHandler;
import com.http.client.rest.template.model.IHttpResponse;
import com.http.client.rest.template.services.executors.HttpRequestExecutor;
import com.http.client.rest.template.services.executors.HttpRequestInvoker;
import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpRestTemplateClient {

    @Autowired
    private HttpRequestInvoker httpRequestInvoker;

    public IHttpResponse getRequest(String url){
        GetHttpRequest getHttpRequest = new GetHttpRequest(url);
        return this.execute(new HttpRequestExecutor<>(getHttpRequest, new DefaultResponseHandler()));
    }

    public IHttpResponse getRequest(String url, ResponseHandler responseHandler){
        GetHttpRequest getHttpRequest = new GetHttpRequest(url);
        return this.execute(new HttpRequestExecutor<>(getHttpRequest, responseHandler));
    }

    public IHttpResponse getRequest(String url, Header[] headers, ResponseHandler responseHandler){
        GetHttpRequest getHttpRequest = new GetHttpRequest(url);
        if(headers != null ){
            getHttpRequest.setHeaders(headers);
        }
        return this.execute(new HttpRequestExecutor<>(getHttpRequest, responseHandler));
    }

    public <T> T postRequest(String url, String requestBody) {
        PostHttpRequest postHttpRequest = new PostHttpRequest(url, requestBody);
        return this.execute(new HttpRequestExecutor<>(postHttpRequest, new DefaultResponseHandler()));
    }

    public <T> T postRequest(String url, String requestBody, ResponseHandler<T> responseHandler) {
        PostHttpRequest postHttpRequest = new PostHttpRequest(url, requestBody);
        return this.execute(new HttpRequestExecutor<>(postHttpRequest, responseHandler));
    }

    public <T> T postRequest(String url, String requestBody, Header[] headers, ResponseHandler<T> responseHandler) {
        PostHttpRequest postHttpRequest = new PostHttpRequest(url, requestBody);
        if(headers != null ){
            postHttpRequest.setHeaders(headers);
        }
        return this.execute(new HttpRequestExecutor<>(postHttpRequest, responseHandler));
    }

    public <T> T deleteRequest(String url, String requestBody) {
        DeleteHttpRequest postHttpRequest = new DeleteHttpRequest(url);
        return this.execute(new HttpRequestExecutor<>(postHttpRequest, new DefaultResponseHandler()));
    }

    public <T> T deleteRequest(String url, String requestBody, ResponseHandler<T> responseHandler) {
        DeleteHttpRequest postHttpRequest = new DeleteHttpRequest(url);
        return this.execute(new HttpRequestExecutor<>(postHttpRequest, responseHandler));
    }

    public <T> T deleteRequest(String url, String requestBody, Header[] headers, ResponseHandler<T> responseHandler) {
        DeleteHttpRequest postHttpRequest = new DeleteHttpRequest(url);
        if(headers != null ){
            postHttpRequest.setHeaders(headers);
        }
        return this.execute(new HttpRequestExecutor<>(postHttpRequest, responseHandler));
    }

    private <T> T execute(HttpRequestExecutor<T> httpRequestExecutor) {
        return httpRequestInvoker.invoke(httpRequestExecutor);
    }


}
