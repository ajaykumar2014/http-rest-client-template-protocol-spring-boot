package com.http.client.rest.template.handlers;

import com.http.client.rest.template.exceptions.HttpRestTemplateException;
import com.http.client.rest.template.helper.JSONUtils;
import com.http.client.rest.template.model.HttpOkResponse;
import com.http.client.rest.template.model.IHttpResponse;
import com.http.client.rest.template.model.StatusCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.Reflection;

import java.io.IOException;

import static com.http.client.rest.template.helper.APIUtils.format;

public abstract class AbstractHttpResponseHandler<T> implements ResponseHandler<IHttpResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpResponseHandler.class);
    private HttpResponse response;

    public AbstractHttpResponseHandler() {
    }

    public IHttpResponse handleResponse(HttpResponse response) throws IOException {
        this.response = response;
        StatusLine statusLine = this.response.getStatusLine();
        HttpEntity httpEntity = this.response.getEntity();
        String callbackResponse = httpEntity == null ? "{}" : EntityUtils.toString(httpEntity);

        if (statusLine.getStatusCode() >= 300) {
            LOGGER.error(format("DM9 API is failed to call due to http StatusCode - {0} and reasons - {1} and response - {2}", statusLine.getStatusCode(), statusLine.getReasonPhrase(), callbackResponse));
            if (statusLine.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                throw new HttpRestTemplateException(StatusCode.AUTHENTICATION_ERROR,statusLine.getReasonPhrase());
            }
            String errorReason = extractFailedMessage(callbackResponse, statusLine);
            if (statusLine.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
                throw new HttpRestTemplateException(StatusCode.AUTHORIZATION_ERROR, errorReason);
            }
            throw new HttpResponseException(statusLine.getStatusCode(), errorReason);
        } else {
            IHttpResponse res = this.handleEntity(callbackResponse);
            return this.handleEntity(callbackResponse);

        }
    }

    public abstract IHttpResponse handleEntity(String var1) throws IOException;


    protected String extractFailedMessage(String response, StatusLine statusLine) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return JSONUtils.toString(jsonObject);
        } catch (JSONException e) {
            return statusLine.getReasonPhrase();
        }
    }

}
