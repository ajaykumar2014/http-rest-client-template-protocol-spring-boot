package com.http.client.rest.template.model;

public enum StatusCode {


    /**
     *
     * Declared custom errors status code with message that will map with specific http response code.
     *
     */
    INTERNAL_ERRORS(400,"Oops !! Something is went wrong here"),
    AUTHENTICATION_ERROR(401,"Authenticate failed"),
    AUTHORIZATION_ERROR(401,"Authorization failed");

    private int code;
    private String message;


    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusCode valueOf(int statusCode) {
        for (StatusCode sCode : values()) {
            if (sCode.code == statusCode) return sCode;
        }
        return INTERNAL_ERRORS;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.code + " : " + this.message;
    }
}
