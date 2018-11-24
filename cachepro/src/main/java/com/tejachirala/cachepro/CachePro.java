package com.tejachirala.cachepro;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tejachirala on 21/11/18
 */
public class CachePro {

    private static CachePro cachePro;
    private ExecutorService pool;
    private Context context;
    private MemoryCache memoryCache;
    static Map<String, List<Request>> requestMap;

    public static final String KEY_URL = "url";
    public static final int SEND_BITMAP = 300;
    public static final int SEND_ERROR = 301;

    static Handler handler = new Handler(Looper.getMainLooper());

    private CachePro(Context context, ExecutorService executorService, MemoryCache memoryCache,
                     Map<String, List<Request>> requestMap) {
        this.context = context;
        this.memoryCache = memoryCache;
        pool = executorService;
        this.requestMap = requestMap;
    }

    public static CachePro with(Context context) {

        if (cachePro == null) {
            cachePro = new Builder(context).build();
        }

        return cachePro;
    }

    public RequestCreator load(String url) {
        return new RequestCreator(url, memoryCache, pool);
    }


    /**
     * Cancel all the requests with the tag name provided
     */
    public void cancelRequestsWithTag(@NonNull String tag) {

        Iterator<Map.Entry<String, List<Request>>> mapIterator = requestMap.entrySet().iterator();
        while (mapIterator.hasNext()) {

            Map.Entry<String, List<Request>> entry = mapIterator.next();

            Iterator<Request> requestIterator = entry.getValue().iterator();
            while (requestIterator.hasNext()) {

                Request request = requestIterator.next();
                if (tag.equalsIgnoreCase(request.tag)) {
                    if (entry.getValue().size() == 1) {
                        if (!request.future.isDone()) {
                            request.future.cancel(true);
                        }
                    }
                    requestIterator.remove();
                }

            }

            if (entry.getValue().isEmpty()) {
                mapIterator.remove();
            }
        }
    }

    /**
     * Removes content from cache using the url
     */
    public void invalidate(String url) {

        if (memoryCache.get(url) != null) {
            memoryCache.remove(url);
        }

    }

    static class Builder {

        private ExecutorService pool;
        private Context context;
        private MemoryCache memoryCache;
        private Map<String, List<Request>> requestMap;

        public Builder(Context context) {

            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        public CachePro build() {

            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            memoryCache = new MemoryCache(cacheSize);

            requestMap = Collections.synchronizedMap(new HashMap<String, List<Request>>());

            pool = Executors.newFixedThreadPool(10);
            return new CachePro(context, pool, memoryCache, requestMap);

        }

    }


}
