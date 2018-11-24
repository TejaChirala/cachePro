package com.tejachirala.cachepro;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tejachirala on 22/11/18
 */
public class Utils {

    public static void sendBitmap(Bitmap bitmap, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            for (Request request : requestMap.get(url)) {

                BitmapRequest bitmapRequest = (BitmapRequest) request;
                bitmapRequest.imageViewWeakReference.get().setImageBitmap(bitmap);
                if (bitmapRequest.completionListener != null) {
                    bitmapRequest.completionListener.onCompleted(true, null, bitmap, LoadedFrom.NETWORK);
                }

            }

        }
        requestMap.remove(url);

    }

    public static void sendBitmapException(Exception e, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            for (Request request : requestMap.get(url)) {

                BitmapRequest bitmapRequest = (BitmapRequest) request;

                if (isValidResId(bitmapRequest.errorResId)) {
                    bitmapRequest.imageViewWeakReference.get().setImageResource(bitmapRequest.errorResId);
                }

                if (bitmapRequest.completionListener != null) {
                    bitmapRequest.completionListener.onCompleted(false, e, null, LoadedFrom.NETWORK);
                }

            }

        }

        requestMap.remove(url);

    }

    public static void sendJSONObject(JSONObject jsonObject, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            for (Request request : requestMap.get(url)) {

                if (request.completionListener != null) {
                    request.completionListener.onCompleted(true, null, jsonObject, LoadedFrom.NETWORK);
                }

            }

        }

        requestMap.remove(url);

    }

    public static void sendJSONArray(JSONArray jsonArray, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            for (Request request : requestMap.get(url)) {

                if (request.completionListener != null) {
                    request.completionListener.onCompleted(true, null, jsonArray, LoadedFrom.NETWORK);
                }

            }

        }

        requestMap.remove(url);

    }

    public static void sendException(Exception e, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            for (Request request : requestMap.get(url)) {

                if (request.completionListener != null) {
                    request.completionListener.onCompleted(false, e, null, LoadedFrom.NETWORK);
                }

            }

        }

        requestMap.remove(url);
    }

    public static void addBitmapRequestToMap(BitmapRequest bitmapRequest, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            bitmapRequest.future = requestMap.get(url).get(0).future;
            requestMap.get(url).add(bitmapRequest);

        } else {

            List<Request> requests = new ArrayList<>();
            requests.add(bitmapRequest);
            requestMap.put(url, requests);

        }

    }

    public static void addRequestToMap(Request request, String url) {

        Map<String, List<Request>> requestMap = CachePro.requestMap;

        if (requestMap.containsKey(url)) {

            request.future = requestMap.get(url).get(0).future;
            requestMap.get(url).add(request);

        } else {

            List<Request> requests = new ArrayList<>();
            requests.add(request);
            requestMap.put(url, requests);

        }

    }

    public static boolean isValidResId(int resId) {
        return resId != -1;
    }

    public static boolean isMapContainsRequest(String url) {
        return CachePro.requestMap.containsKey(url);
    }

    public static String getJSONFromURL(String url) throws IOException {

        URL bitmapURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) bitmapURL.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();

        BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
        StringBuilder sBuilder = new StringBuilder();

        String line = null;
        while ((line = bReader.readLine()) != null) {
            sBuilder.append(line).append("\n");
        }

        inputStream.close();
        return sBuilder.toString();

    }


}
