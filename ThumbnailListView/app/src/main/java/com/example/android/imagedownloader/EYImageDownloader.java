package com.example.android.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eyang on 7/23/15.
 */
public class EYImageDownloader {
    private static EYImageDownloader instance;
    private LruCache<String, Bitmap> mMemoryCache;

    private EYImageDownloader(){

        final int cacheSize = 4 * 1024 * 1024; // 4MiB
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap){
                return bitmap.getByteCount();
            }
        };
    }

    public static EYImageDownloader getInstance(){
        if (instance==null){
            synchronized (EYImageDownloader.class){
                if (instance==null){
                    instance = new EYImageDownloader();
                }
            }
        }
        return instance;
    }

    public void download(String url, ViewHolder viewHolder) {
        if (viewHolder.taskWeakReference!=null){
            AsyncTask task = viewHolder.taskWeakReference.get();
//            if (task!=null && task.url!=null && task.url.equalsIgnoreCase(url)){//if same url, ingore, but sometimes the item is not recycled but the image is gone
//                Log.e("same url!!!!!!", "same url!!!!!!");
//                return;
//            }
            if (task!=null){
                task.cancel(true);
                viewHolder.taskWeakReference = null;
                Log.e("Cancel Task!!!!!!", "Cancel Task!!!!!!");
            }
        }
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap!=null){
            viewHolder.imageView.setImageBitmap(bitmap);
            Log.e("hit cache!!!!!!", "hit cache!!!!!!");
            return;
        }
        BitmapDownloaderTask newTask = new BitmapDownloaderTask(viewHolder);
        viewHolder.taskWeakReference = new WeakReference<AsyncTask>(newTask);
        newTask.execute(url);
    }

    private Bitmap getBitmapFromCache(String url){
        return mMemoryCache.get(url);
    }

    private void addBitmapToCache(String url, Bitmap bitmap){
        if(getBitmapFromCache(url)==null){
            mMemoryCache.put(url, bitmap);
        }
    }

    public class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ViewHolder> viewHolderWeakReference;
        public String url;

        public BitmapDownloaderTask(ViewHolder viewHolder) {
            viewHolderWeakReference = new WeakReference<ViewHolder>(viewHolder);
        }

        /**
         * Actual download method.
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            url = params[0];
            return downloadBitmap(url);
        }

        /**
         * Once the image is downloaded, associates it to the imageView
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            addBitmapToCache(url, bitmap);

            if (viewHolderWeakReference != null) {
                ViewHolder viewHolder = viewHolderWeakReference.get();
                if(viewHolder==null){
                    Log.e("viewHolder is empty!!!", "viewHolder is empt!!!!");
                    return;
                }
                if ( viewHolder.taskWeakReference.get()!=null && this != viewHolder.taskWeakReference.get() ) {
                    Log.e("Not current task!!!!!!", "Not current task!!!!!!!");
                }else{
                    viewHolder.imageView.setImageBitmap(bitmap);
                }
                if (viewHolder.taskWeakReference.get()!=null){
                    viewHolder.taskWeakReference = null;
                }
            }
        }
    }

    static Bitmap downloadBitmap(String urlStr) {
        HttpURLConnection urlConnection = null;
        InputStream input = null;
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(6 * 1000);
            input = urlConnection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                if (input != null){
                    input.close();
                }
            }catch (final IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
