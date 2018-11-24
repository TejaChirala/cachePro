package com.tejachirala.cachepro;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by tejachirala on 22/11/18
 */
class JSONObjectWorker implements Runnable {

    private String url;
    private MemoryCache memoryCache;

    public JSONObjectWorker(String url, MemoryCache memoryCache) {
        this.url = url;
        this.memoryCache = memoryCache;
    }


    @Override
    public void run() {


        try {
            String result = Utils.getJSONFromURL(url);
            final JSONObject jsonObject = new JSONObject(result);

            //Save the bitmap to memory cache
            memoryCache.put(url, jsonObject);

            CachePro.handler.post(new Runnable() {
                @Override
                public void run() {
                    Utils.sendJSONObject(jsonObject, url);
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
