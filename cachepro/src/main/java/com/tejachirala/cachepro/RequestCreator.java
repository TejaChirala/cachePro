package com.tejachirala.cachepro;

import java.util.concurrent.ExecutorService;

/**
 * Created by tejachirala on 22/11/18
 */
public class RequestCreator {

    private String url;
    private MemoryCache memoryCache;
    private ExecutorService pool;
    private int resultContentType;

    RequestCreator(String url, MemoryCache memoryCache, ExecutorService pool) {

        this.url = url;
        this.memoryCache = memoryCache;
        this.pool = pool;
    }


    public ImageOptionsBuilder getAsImageBitmap() {

        resultContentType = ContentType.TYPE_IMAGE;

        return new ImageOptionsBuilder(url, memoryCache, pool);

    }


    public JSONObjectOptionBuilder getAsJSONObject() {

        resultContentType = ContentType.TYPE_JSON_OBJECT;

        return new JSONObjectOptionBuilder(url, memoryCache, pool);

    }

    public JSONArrayOptionBuilder getAsJSONArray() {

        resultContentType = ContentType.TYPE_JSON_ARRAY;

        return new JSONArrayOptionBuilder(url, memoryCache, pool);

    }

}
