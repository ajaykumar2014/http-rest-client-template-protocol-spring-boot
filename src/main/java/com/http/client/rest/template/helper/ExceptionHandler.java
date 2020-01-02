package com.http.client.rest.template.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.http.client.rest.template.exceptions.HttpRestTemplateException;
import com.http.client.rest.template.model.StatusCode;
import org.apache.http.client.HttpResponseException;
import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public final class ExceptionHandler {

    private ExceptionHandler() {
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    public static <T> T unchecked(final ExceptionBearingAction<T> template, Consumer<Exception> exceptionConsumer) {
        T results = null;
        try {
            results = template.doAction();
        } catch (Exception ex) {
            exceptionConsumer.accept(ex);
        }
        return results;
    }

    public static void unchecked(final ExceptionBearingAction template) {
        try {
            template.doAction();
        } catch (Exception ex) {
            throwException(ex);
        }
    }


    public static void throwException(Exception ex) {
        String errorMessage = "";
        if (ex instanceof HttpResponseException) {
            errorMessage = ((HttpResponseException) ex).getStatusCode() + " - " + ex.getMessage();
        } else if (ex instanceof HttpHostConnectException) {
            HttpHostConnectException hostConnectException = (HttpHostConnectException) ex;
            errorMessage = "Host Connection Error:- " + hostConnectException.getMessage();
        } else if (ex instanceof JsonProcessingException) {
            JsonProcessingException jsonProcessingException = ((JsonProcessingException) ex);
            errorMessage = APIUtils.format("JSON Parsing Error at Location [{0}] and messages - {1} ", jsonProcessingException.getLocation(), jsonProcessingException.getMessage());
        } else {
            errorMessage = ex.getClass().getName() + " : " + ex.getMessage();
        }
        LOGGER.error("Error Message :" + errorMessage, ex.fillInStackTrace());
        throw new HttpRestTemplateException(StatusCode.INTERNAL_ERRORS,errorMessage,ex);
    }
}
