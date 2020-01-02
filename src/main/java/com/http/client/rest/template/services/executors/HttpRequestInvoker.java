package com.http.client.rest.template.services.executors;

import com.http.client.rest.template.helper.ExceptionHandler;
import com.http.client.rest.template.services.HttpRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpRequestInvoker {

    @Autowired
    private HttpRestClientService httpRestClientService;

    public <T> T invoke(HttpRequestExecutor<T> httpRequest) {
        return ExceptionHandler.unchecked(() -> httpRequest.execute(httpRestClientService), ex -> ExceptionHandler.throwException(ex));
    }
}
