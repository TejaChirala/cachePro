package com.tejachirala.cachepro;

import java.util.concurrent.Future;

/**
 * Created by tejachirala on 22/11/18
 */
class Request {

    public Future future;
    public String url;
    public String tag;
    public CompletionListener completionListener;

    public Request(Future future, String url, String tag, CompletionListener completionListener) {
        this.future = future;
        this.url = url;
        this.tag = tag;
        this.completionListener = completionListener;
    }

}
