package com.http.client.rest.template.handlers;

import com.http.client.rest.template.model.HttpOkResponse;
import com.http.client.rest.template.model.IHttpResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class DefaultResponseHandler extends AbstractHttpResponseHandler<IHttpResponse> {

    @Override
    public IHttpResponse handleEntity(String var1) throws IOException {
        return new HttpOkResponse(HttpStatus.OK.value(),var1);
    }
}