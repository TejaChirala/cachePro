package com.tejachirala.cachepro;

import android.util.LruCache;

/**
 * Created by tejachirala on 21/11/18
 */
class MemoryCache extends LruCache<String, Object> {

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MemoryCache(int maxSize) {
        super(maxSize);
    }

}
