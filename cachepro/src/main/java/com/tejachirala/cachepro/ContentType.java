package com.tejachirala.cachepro;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tejachirala on 22/11/18
 */
class ContentType {

    public static final int TYPE_IMAGE = 101;
    public static final int TYPE_JSON_OBJECT = 102;
    public static final int TYPE_JSON_ARRAY = 103;


    @IntDef({TYPE_IMAGE, TYPE_JSON_OBJECT, TYPE_JSON_ARRAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

}
