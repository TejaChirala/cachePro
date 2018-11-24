package com.tejachirala.cachepro;

import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.concurrent.Future;

/**
 * Created by tejachirala on 24/11/18
 */
class BitmapRequest extends Request {

    public int placeHolderId;
    public int errorResId;
    public WeakReference<ImageView> imageViewWeakReference;

    public BitmapRequest(Future future, String url, String tag,
                         CompletionListener completionListener,
                         int placeHolderId, int errorResId,
                         WeakReference<ImageView> imageViewWeakReference) {
        super(future, url, tag, completionListener);

        this.placeHolderId = placeHolderId;
        this.errorResId = errorResId;
        this.imageViewWeakReference = imageViewWeakReference;
    }


}
