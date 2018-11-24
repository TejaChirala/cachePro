package com.tejachirala.cachecontent;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tejachirala.cachecontent.model.Account;
import com.tejachirala.cachepro.CachePro;
import com.tejachirala.cachepro.CompletionListener;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tejachirala on 24/11/18
 */
@RunWith(AndroidJUnit4.class)
public class JSONArrayTestCase {

    @Test
    public void checkJSONArray() throws InterruptedException {

        Context appContext = InstrumentationRegistry.getTargetContext();
        final Semaphore semaphore = new Semaphore(1);

        CachePro.with(appContext)
                .load("http://pastebin.com/raw/wgkJgazE")
                .getAsJSONArray()
                .setCompletionListener(new CompletionListener<JSONArray>() {
                    @Override
                    public void onCompleted(boolean isSuccess, Exception e, JSONArray result, String loadedFrom) {
                        assertNotNull(result);

                        Type collectionType = new TypeToken<List<Account>>() {
                        }.getType();
                        List<Account> accountList = new Gson().fromJson(result.toString(), collectionType);

                        assertEquals("4kQA1aQK8-Y", accountList.get(0).getId());

                        semaphore.release();
                    }
                });
        semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);

    }

}
