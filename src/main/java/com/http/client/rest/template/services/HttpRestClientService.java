package com.http.client.rest.template.services;

import com.http.client.rest.template.services.providers.HttpClientProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpRestClientService implements HttpClientRequestor {


    @Autowired
    private HttpClientProvider httpClientProvider;

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        return httpClientProvider.getClient().execute(httpUriRequest,responseHandler);
    }
}
