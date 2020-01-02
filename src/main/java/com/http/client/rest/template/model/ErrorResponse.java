package com.http.client.rest.template.model;


public class ErrorResponse implements IResponse {

    private int code;
    private String message;
    private String details;

    public ErrorResponse() {

    }

    public ErrorResponse(StatusCode status, String details) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.details = details;
    }

    public ErrorResponse(int status, String message, String details) {
        this.code = status;
        this.message = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "ErrorResponse [code=" + code + ", message=" + message + "]";
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}
