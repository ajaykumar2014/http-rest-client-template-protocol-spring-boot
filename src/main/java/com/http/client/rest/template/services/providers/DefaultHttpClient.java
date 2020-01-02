package com.http.client.rest.template.services.providers;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Collection;

public class DefaultHttpClient implements IHttpClient {

    private final Collection<? extends Header> defaultHeaders;
    private final int timeout;

    public DefaultHttpClient(Collection<? extends Header> defaultHeaders, int timeout) {
        this.defaultHeaders = defaultHeaders;
        this.timeout = timeout;
    }

    public CloseableHttpClient getClient() {

        return HttpClientBuilder
                .create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(timeout)
                        .setSocketTimeout(timeout)
                        .build())
                .useSystemProperties()
                .setDefaultHeaders(defaultHeaders)
                .build();
    }
}
