package com.http.client.rest.template.services.providers;

import org.apache.http.impl.client.CloseableHttpClient;

public interface IHttpClient {
    CloseableHttpClient getClient();
}
