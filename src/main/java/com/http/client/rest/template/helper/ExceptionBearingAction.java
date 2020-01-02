package com.http.client.rest.template.helper;

@FunctionalInterface
public interface ExceptionBearingAction<T> {

    T doAction() throws Exception;
}
