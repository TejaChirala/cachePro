package com.tejachirala.cachepro;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by tejachirala on 22/11/18
 */
public class ImageOptionsBuilder {

    private String url;
    private MemoryCache memoryCache;
    private String tag;
    private ExecutorService pool;
    private int placeHolderId = -1;
    private int errorResourceId = -1;


    public ImageOptionsBuilder(String url, MemoryCache memoryCache, ExecutorService pool) {

        this.url = url;
        this.memoryCache = memoryCache;
        this.pool = pool;
    }

    public ImageOptionsBuilder withPlaceHolder(int placeHolder) {
        this.placeHolderId = placeHolder;
        return this;
    }

    public ImageOptionsBuilder onError(int errorResourceId) {
        this.errorResourceId = errorResourceId;
        return this;
    }

    public ImageOptionsBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public void into(ImageView imageView) {
        into(imageView, null);
    }

    public void into(ImageView imageView, CompletionListener<Bitmap> completionListener) {

        WeakReference<ImageView> weakReference = new WeakReference<>(imageView);
        if (Utils.isValidResId(placeHolderId)) {
            imageView.setImageResource(placeHolderId);
        }

        if (memoryCache.get(url) != null) {
            imageView.setImageBitmap((Bitmap) memoryCache.get(url));
            if (completionListener != null) {
                completionListener.onCompleted(true, null, (Bitmap) memoryCache.get(url), LoadedFrom.MEMORY);
            }
        } else {

            if (Utils.isMapContainsRequest(url)) {
                Utils.addBitmapRequestToMap(
                        new BitmapRequest(null, url, tag, completionListener,
                                placeHolderId, errorResourceId, weakReference), url);

            } else {
                BitmapWorker worker = new BitmapWorker(url, memoryCache);
                Future future = pool.submit(worker);
                Utils.addBitmapRequestToMap(
                        new BitmapRequest(future, url, tag, completionListener,
                        placeHolderId, errorResourceId, weakReference),
                        url);

            }

        }

    }

}
