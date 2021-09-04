package com.example.componentstry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class RequestHandler {

    @SuppressLint("StaticFieldLeak")
    static RequestHandler instance = null;

    Context context;
    RequestQueue requestQueue;
    ImageLoader imageLoader;

    RequestHandler(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(
                requestQueue,
                new ImageLoader.ImageCache() {
                    final LruCache<String, Bitmap> cache = new LruCache<>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RequestHandler getInstance(Context context) {
        if (instance == null) instance = new RequestHandler(context);

        return instance;
    }

    public static synchronized RequestHandler getInstance() {
        return instance;
    }

    public <T> void add(Request<T> request) {
        requestQueue.add(request);
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
