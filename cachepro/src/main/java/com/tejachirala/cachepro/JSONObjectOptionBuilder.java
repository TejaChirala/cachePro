package com.tejachirala.cachepro;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by tejachirala on 22/11/18
 */
public class JSONObjectOptionBuilder {

    private String url;
    private MemoryCache memoryCache;
    private String tag;
    private ExecutorService pool;

    public JSONObjectOptionBuilder(String url, MemoryCache memoryCache, ExecutorService pool) {

        this.url = url;
        this.memoryCache = memoryCache;
        this.pool = pool;
    }

    public JSONObjectOptionBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setCompletionListener(CompletionListener<JSONObject> completionListener) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (memoryCache.get(url) != null) {
            if (completionListener != null) {
                completionListener.onCompleted(true, null, (JSONObject) memoryCache.get(url), LoadedFrom.MEMORY);
            }
        } else {

            if (Utils.isMapContainsRequest(url)) {

                Utils.addRequestToMap(new Request(null, url, tag, completionListener), url);

            } else {

                JSONObjectWorker worker = new JSONObjectWorker(url, memoryCache);
                Future future = pool.submit(worker);
                Utils.addRequestToMap(new Request(future, url, tag, completionListener), url);

            }

        }


    }

}
