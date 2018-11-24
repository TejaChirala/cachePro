package com.tejachirala.cachepro;

/**
 * Created by tejachirala on 22/11/18
 */
public interface CompletionListener<T> {

    public void onCompleted(boolean isSuccess, Exception e, T result, String loadedFrom);

}
