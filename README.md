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
 
 ## Tests 
 
 ### Bitmap
 ```
final Semaphore semaphore = new Semaphore(1);

CachePro.with(appContext)
	.load("https://api.unsplash.com/users/nicholaskampouris")
	.getAsImageBitmap()
	.into(new ImageView(appContext), new CompletionListener<Bitmap>() {
	    @Override
	    public void onCompleted(boolean isSuccess, Exception e, Bitmap bitmap, String loadedFrom) {
		if (isSuccess) {
		    assertNotNull(bitmap);
		} else {
		    assertNotNull(e);
		}
		semaphore.release();
	    }
	});
semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);
 
 ```
 
 ### Test to check where the content is loaded from
 
 ```
 String url = "https://api.unsplash.com/users/nicholaskampouris";

//Clear the content from the cache if already exists
CachePro.with(appContext)
	.invalidate(url);

//Allowing only one thread to access content
final Semaphore semaphore = new Semaphore(1);
CachePro.with(appContext)
	.load("")
	.getAsImageBitmap()
	.into(new ImageView(appContext), new CompletionListener<Bitmap>() {
	    @Override
	    public void onCompleted(boolean isSuccess, Exception e, Bitmap result, String loadedFrom) {
		assertEquals(loadedFrom, LoadedFrom.NETWORK);
		semaphore.release();
	    }
	});
semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);

CachePro.with(appContext)
	.load("https://api.unsplash.com/users/nicholaskampouris")
	.getAsImageBitmap()
	.into(new ImageView(appContext), new CompletionListener<Bitmap>() {
	    @Override
	    public void onCompleted(boolean isSuccess, Exception e, Bitmap bitmap, String loadedFrom) {
	       assertEquals(loadedFrom, LoadedFrom.MEMORY);
	       semaphore.release();
	    }
	});
semaphore.tryAcquire(10000, TimeUnit.MILLISECONDS);
```	
	
 
 ### JSONObject
 
 ```
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
 
 ```
 
 ### JSONArray
 
 ```
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

 ```
check out the tests ![here](https://github.com/TejaChirala/cachePro/tree/master/app/src/androidTest/java/com/tejachirala/cachecontent)
