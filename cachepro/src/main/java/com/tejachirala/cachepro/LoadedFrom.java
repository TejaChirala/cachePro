package com.tejachirala.cachepro;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tejachirala on 22/11/18
 */
public class LoadedFrom {

    public static final String MEMORY = "Memory";
    public static final String NETWORK = "Network";


    @StringDef({MEMORY, NETWORK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

}
