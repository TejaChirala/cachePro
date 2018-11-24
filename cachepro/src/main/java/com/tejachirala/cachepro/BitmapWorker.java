package com.tejachirala.cachepro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tejachirala on 22/11/18
 */
class BitmapWorker implements Runnable {

    private String url;
    private MemoryCache memoryCache;

    public BitmapWorker(String url, MemoryCache memoryCache) {
        this.url = url;
        this.memoryCache = memoryCache;
    }


    @Override
    public void run() {

        try {
            URL bitmapURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) bitmapURL.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(input);

            //Save the bitmap to memory cache
            memoryCache.put(url, bitmap);


            CachePro.handler.post(new Runnable() {
                @Override
                public void run() {
                    Utils.sendBitmap(bitmap, url);
                }
            });


        } catch (final IOException e) {

            CachePro.handler.post(new Runnable() {
                @Override
                public void run() {
                    Utils.sendBitmapException(e, url);
                }
            });

        }
    }

}

