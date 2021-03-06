package com.ctp.cdi.query.util;

import java.text.MessageFormat;

/**
 *
 * @author thomashug
 */
public final class QueryUtils {
    
    private QueryUtils() {
    }
    
    private static final String KEYWORD_SPLITTER = "({0})(?=[A-Z])";
    
    public static String[] splitByKeyword(String query, String keyword) {
        return query.split(MessageFormat.format(KEYWORD_SPLITTER, keyword));
    }
    
    public static String uncapitalize(String value) {
        if (value == null) {
            return null;
        }
        if (value.length() == 1) {
            return value.toLowerCase();
        }
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }
    
    public static boolean isEmpty(String text) {
        return text == null || "".equals(text);
    }
    
    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }
    
}
