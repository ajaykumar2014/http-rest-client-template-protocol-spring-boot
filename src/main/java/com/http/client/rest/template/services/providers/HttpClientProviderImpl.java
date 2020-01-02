package com.http.client.rest.template.services.providers;

import org.apache.http.Header;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class HttpClientProviderImpl implements HttpClientProvider {
    private final IHttpClient httpClient = new DefaultHttpClient(getDM9Headers(), 5000);

    @Override
    public CloseableHttpClient getClient() {
        return httpClient.getClient();
    }

    private final Collection<? extends Header> getDM9Headers() {
        return Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType()), new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType()));
    }}
