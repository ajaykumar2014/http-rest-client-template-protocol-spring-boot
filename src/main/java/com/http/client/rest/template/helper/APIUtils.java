package com.http.client.rest.template.helper;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.text.MessageFormat;

public final class APIUtils {

    private APIUtils() {
    }

    public static String format(String s, Object... args) {
        return new MessageFormat(s).format(args);
    }


    public static boolean isBlank(String... strings) {
        if (strings == null) {
            return true;
        }
        boolean flags = true;
        for (String str : strings) {
            if (isNotEmpty(str)) {
                flags = false;
                break;
            }
        }
        return flags;
    }

    public static boolean isNotEmpty(Object obj) {
        return (obj != null && !"".equals(obj));
    }



    public static boolean isEmpty(ArrayNode arrayNode){
        return (arrayNode == null || arrayNode.isNull() || arrayNode.size() == 0);
    }


}
