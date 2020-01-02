package com.http.client.rest.controllers;

import com.http.client.rest.template.exceptions.HttpRestTemplateException;
import com.http.client.rest.template.model.ErrorResponse;
import com.http.client.rest.template.model.HttpFailedResponse;
import com.http.client.rest.template.model.IHttpResponse;
import com.http.client.rest.template.model.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    protected static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public ResponseEntity getBadRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(HttpRestTemplateException.class)
    public ResponseEntity<ErrorResponse> onException(HttpRestTemplateException e) {
        LOGGER.error("Automate Actions resource is failed due to Status Code [" + e.getStatusCode() + "], reason [" + e.getMessage() + "].", e.getCause());
        final String details = e.getMessage() != null ? e.getMessage() : "" + e;
        ErrorResponse errorResponse = new ErrorResponse(StatusCode.valueOf(e.getStatusCode()), details);
        return getResponseEntity(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> onException(Exception e) {
        LOGGER.error("Automate Actions resource failed.", e.getCause());
        final String details = e.getMessage() != null ? e.getMessage() : "" + e;
        ErrorResponse errorResponse = new ErrorResponse(StatusCode.INTERNAL_ERRORS, details);
        return getResponseEntity(errorResponse);
    }


    protected ResponseEntity<?> getHttpResponse(IHttpResponse httpResponse) {
        if (httpResponse instanceof HttpFailedResponse) {
            HttpFailedResponse errorResponse = (HttpFailedResponse) httpResponse;
            return ResponseEntity.status(httpResponse.statusCode()).contentType(MediaType.APPLICATION_JSON_UTF8).body(errorResponse);
        }
        return ResponseEntity.status(httpResponse.statusCode()).body(httpResponse.getBody());
    }

    private ResponseEntity<ErrorResponse> getResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity(errorResponse, HttpStatus.valueOf(200));
    }
}