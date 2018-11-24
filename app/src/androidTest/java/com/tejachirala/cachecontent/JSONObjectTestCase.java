package com.tejachirala.cachecontent;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tejachirala.cachepro.CachePro;
import com.tejachirala.cachepro.CompletionListener;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * Created by tejachirala on 24/11/18
 */
@RunWith(AndroidJUnit4.class)
public class JSONObjectTestCase {

    @Test
    public void checkJSONArray() throws InterruptedException {

        Context appContext = InstrumentationRegistry.getTargetContext();
        final Semaphore semaphore = new Semaphore(1);

        CachePro.with(appContext)
                .load("http://api.plos.org/search?q=title:DNA")
                .getAsJSONObject()
                .setCompletionListener(new CompletionListener<JSONObject>() {
                    @Override
                    public void onCompleted(boolean isSuccess, Exception e, JSONObject result, String loadedFrom) {
                        assertNotNull(result);
                        semaphore.release();
                    }
                });
        semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);

    }

}

