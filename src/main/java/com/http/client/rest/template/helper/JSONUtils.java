package com.http.client.rest.template.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class JSONUtils {

    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = ThreadLocal.withInitial(() -> new ObjectMapper());

    private JSONUtils() {
        // Restricted to created an Object.
    }

    public static final ObjectNode createObjectNode() {
        return get().createObjectNode();
    }

    public static final ArrayNode createArrayNode() {
        return get().createArrayNode();
    }

    private static final ObjectMapper get() {
        return OBJECT_MAPPER_THREAD_LOCAL.get();
    }

    public static String toString(Object object) {
        if (object == null) {
        	return "";
        }
        return ExceptionHandler.unchecked(() -> get().writeValueAsString(object), ex -> ExceptionHandler.throwException(ex));
    }

    public static String toString(Object object, JsonInclude.Include incl) {
        if (object == null) {
        	return "";
        }
        return ExceptionHandler.unchecked(() -> get().setSerializationInclusion(incl).writeValueAsString(object), ex -> ExceptionHandler.throwException(ex));
    }

    public static final <T> T toClass(String jsonAsString, Class<?> zlass) {
        return ExceptionHandler.unchecked(() -> get().readerFor(zlass).readValue(jsonAsString), ex -> ExceptionHandler.throwException(ex));
    }

    public static JsonNode toJson(Object object) {
        return ExceptionHandler.unchecked(() -> get().convertValue(object, JsonNode.class), ex -> ExceptionHandler.throwException(ex));
    }

    public static JsonNode toJson(byte[] bytes) {
        return ExceptionHandler.unchecked(() -> get().readTree(bytes), ex -> ExceptionHandler.throwException(ex));
    }

    public static <T> T toClass(JsonNode object, Class<T> zassType) {
        return ExceptionHandler.unchecked(() -> get().convertValue(object, zassType), ex -> ExceptionHandler.throwException(ex));
    }

    public static JsonNode toJson(String payload) {
        return ExceptionHandler.unchecked(() -> get().readTree(payload), ex -> ExceptionHandler.throwException(ex));
    }

}
