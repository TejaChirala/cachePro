package com.tejachirala.cachepro;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by tejachirala on 22/11/18
 */
public class JSONArrayOptionBuilder {

    private String url;
    private MemoryCache memoryCache;
    private String tag;
    private ExecutorService pool;

    public JSONArrayOptionBuilder(String url, MemoryCache memoryCache, ExecutorService pool) {

        this.url = url;
        this.memoryCache = memoryCache;
        this.pool = pool;
    }

    public JSONArrayOptionBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setCompletionListener(CompletionListener<JSONArray> completionListener) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (memoryCache.get(url) != null) {
            if (completionListener != null) {
                completionListener.onCompleted(true, null, (JSONArray) memoryCache.get(url), LoadedFrom.MEMORY);
            }
        } else {

            if (Utils.isMapContainsRequest(url)) {

                Utils.addRequestToMap(new Request(null, url, tag, completionListener), url);

            } else {

                JSONArrayWorker worker = new JSONArrayWorker(url, memoryCache);
                Future future = pool.submit(worker);
                Utils.addRequestToMap(new Request(future, url, tag, completionListener), url);

            }

        }


    }

}
