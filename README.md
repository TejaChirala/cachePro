# cachePro

*A library to download and cache images and JSON asynchrounously. CachePro effectively handles multiple requests
allowing only one network call per url while the other requests recieve the same network result.*

Checkout the sample app(CacheContent) with use of cachePro with Ripple, Animation, Swipe to refresh layout.

![Screen shot](https://github.com/TejaChirala/cachePro/blob/master/cache_content.png)

## Bitmap
```
CachePro.with(context)
	.load(url)
        .getAsImageBitmap()
        .into(imageView);
```	
	
        
## Bitmap with placeholder and error resource id's

```
CachePro.with(context)
        .load(url)
        .getAsImageBitmap()
        .withPlaceHolder(R.drawable.placeholderId)
        .onError(R.drawable.errorResId)
        .into(imageView);
```        
        
## Bitmap with callback

```
CachePro.with(context)
        .load(url)
        .getAsImageBitmap()
        .into(imageView, new CompletionListener<Bitmap>() {
            @Override
            public void onCompleted(boolean isSuccess, Exception e, Bitmap result, String loadedFrom) {
                  //your code here
            }
        });
```  
  
## JSONObject

```
CachePro.with(context)
        .load(url)
        .getAsJSONObject()
        .setCompletionListener(new CompletionListener<JSONObject>() {
            @Override
            public void onCompleted(boolean isSuccess, Exception e, JSONObject result, String loadedFrom) {
                  //Your code here
            }
        });
```        
        
## JSONArray

```
CachePro.with(context)
        .load(url)
        .getAsJSONArray()
        .setCompletionListener(new CompletionListener<JSONArray>() {
            @Override
            public void onCompleted(boolean isSuccess, Exception e, JSONArray result, String loadedFrom) {
                  //Your code here
            }
        });   
```        
        
## Setting Tag to requests

```
CachePro.with(context)
        .load(url)
        .getAsImageBitmap()
        .setTag("tag")
        .into(imageView);       
 ```       
    
    
 ## Cancel requests with Tag
 
 ```
 CachePro.with(context)
         .cancelRequestsWithTag("tag");
 ```       

