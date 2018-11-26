package com.tejachirala.cachepro;

import java.util.concurrent.ExecutorService;

/**
 * Created by tejachirala on 22/11/18
 */
public class RequestCreator {

    private String url;
    private MemoryCache memoryCache;
    private ExecutorService pool;

    RequestCreator(String url, MemoryCache memoryCache, ExecutorService pool) {

        this.url = url;
        this.memoryCache = memoryCache;
        this.pool = pool;
    }


    public ImageOptionsBuilder getAsImageBitmap() {

        return new ImageOptionsBuilder(url, memoryCache, pool);

    }


    public JSONObjectOptionBuilder getAsJSONObject() {

        return new JSONObjectOptionBuilder(url, memoryCache, pool);

    }

    public JSONArrayOptionBuilder getAsJSONArray() {

        return new JSONArrayOptionBuilder(url, memoryCache, pool);

    }

}
