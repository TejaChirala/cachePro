package com.tejachirala.cachecontent;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.tejachirala.cachepro.CachePro;
import com.tejachirala.cachepro.CompletionListener;
import com.tejachirala.cachepro.LoadedFrom;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tejachirala on 24/11/18
 */
@RunWith(AndroidJUnit4.class)
public class BitmapTestCase {

    @Test
    public void checkBitmap() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        final Semaphore semaphore = new Semaphore(1);

        CachePro.with(appContext)
                .load("https://api.unsplash.com/users/nicholaskampouris")
                .getAsImageBitmap()
                .into(new ImageView(appContext), new CompletionListener<Bitmap>() {
                    @Override
                    public void onCompleted(boolean isSuccess, Exception e, Bitmap bitmap, String loadedFrom) {
                        if (isSuccess) {
                            assertNotNull(bitmap);
                        } else {
                            assertNotNull(e);
                        }
                        semaphore.release();
                    }
                });
        semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void checkBitmapLoadedFrom() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        String url = "https://api.unsplash.com/users/nicholaskampouris";

        //Clear the content from the cache if already exists
        CachePro.with(appContext)
                .invalidate(url);

        //Allowing only one thread to access content
        final Semaphore semaphore = new Semaphore(1);
        CachePro.with(appContext)
                .load("")
                .getAsImageBitmap()
                .into(new ImageView(appContext), new CompletionListener<Bitmap>() {
                    @Override
                    public void onCompleted(boolean isSuccess, Exception e, Bitmap result, String loadedFrom) {
                        assertEquals(loadedFrom, LoadedFrom.NETWORK);
                        semaphore.release();
                    }
                });
        semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);

        CachePro.with(appContext)
                .load("https://api.unsplash.com/users/nicholaskampouris")
                .getAsImageBitmap()
                .into(new ImageView(appContext), new CompletionListener<Bitmap>() {
                    @Override
                    public void onCompleted(boolean isSuccess, Exception e, Bitmap bitmap, String loadedFrom) {
                       assertEquals(loadedFrom, LoadedFrom.MEMORY);
                       semaphore.release();
                    }
                });
        semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);
    }


}
