package com.tejachirala.cachecontent.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Status of a resource that is provided to the UI.
 * <p>
 * These are usually created by the Repository classes where they return
 * {@code LiveData<Resource<T>>} to pass back the latest data to the UI with its fetch status.
 */
public class Status {

    public static final int SUCCESS = 100;
    public static final int ERROR = 101;
    public static final int LOADING = 102;


    @IntDef({SUCCESS, ERROR, LOADING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

}
