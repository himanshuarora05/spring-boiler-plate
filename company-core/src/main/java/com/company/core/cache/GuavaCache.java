package com.company.core.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author mukulbansal
 */
public class GuavaCache {

    private static final GuavaCache INSTANCE;

    static {
        INSTANCE = new GuavaCache();
        INSTANCE.loadingCache = CacheBuilder.newBuilder().maximumSize(100)
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return null;
                    }
                });
    }

    private LoadingCache<String, String> loadingCache;

    public static GuavaCache getINSTANCE() {
        return INSTANCE;
    }

    public LoadingCache getLoadingCache() {
        return loadingCache;
    }
}
