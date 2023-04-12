package com.company.core.util;

/**
 *
 * @author mukulbansal
 *
 */
public class AssertionUtil {

    public static void notEmpty(String string) {
        notEmpty(string, "[Assertion failed] - this argument is required; it must not be null or empty");
    }

    public static void notEmpty(String string, String message) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void areSameClass(Class clazz1, Class clazz2){
        if(!clazz1.getName().equalsIgnoreCase(clazz2.getName())) {
            throw new IllegalArgumentException("[Assertion failed] - Different class objects - " + clazz1.getName() + " , " + clazz2.getName());
        }
    }

}
