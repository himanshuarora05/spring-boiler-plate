package com.company.core.serdes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * @author mukulbansal
 */
public class GsonSerializer {

    private static final Gson           GSON     = new GsonBuilder().disableHtmlEscaping().create();
    private static final GsonSerializer INSTANCE = new GsonSerializer();

    private GsonSerializer() {
    }

    public static GsonSerializer getInstance() {
        return INSTANCE;
    }

    public String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return GSON.toJson(object);
    }

    public String toJson(Object object, Class klass) {
        if (object == null) {
            return null;
        }
        return GSON.toJson(object, klass);
    }

    public <T> T fromJson(String json, Class<T> klass) {
        if (json == null) {
            return null;
        }
        return GSON.fromJson(json, klass);
    }

    public <T> T fromJson(String json, Type type) {
        if (json == null) {
            return null;
        }
        return GSON.fromJson(json, type);
    }
}
