package com.tejachirala.cachepro;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by tejachirala on 22/11/18
 */
class JSONArrayWorker implements Runnable {

    private String url;
    private MemoryCache memoryCache;

    public JSONArrayWorker(String url, MemoryCache memoryCache) {
        this.url = url;
        this.memoryCache = memoryCache;
    }


    @Override
    public void run() {

        try {
            String result = Utils.getJSONFromURL(url);
            final JSONArray jsonArray = new JSONArray(result);

            //Save the bitmap to memory cache
            memoryCache.put(url, jsonArray);

            CachePro.handler.post(new Runnable() {
                @Override
                public void run() {
                    Utils.sendJSONArray(jsonArray, url);
                }
            });


        } catch (IOException | JSONException e) {
            CachePro.handler.post(new Runnable() {
                @Override
                public void run() {
                    Utils.sendException(e, url);
                }
            });
        }

    }

}
