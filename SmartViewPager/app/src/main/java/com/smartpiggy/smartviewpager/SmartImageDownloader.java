package com.smartpiggy.smartviewpager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eyang on 10/20/15.
 */
public class SmartImageDownloader {
        private static SmartImageDownloader instance;
        private LruCache<String, Bitmap> mMemoryCache;

        private SmartImageDownloader(){

            final int cacheSize = 4 * 1024 * 1024; // 4MiB
            mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
                @Override
                protected int sizeOf(String key, Bitmap bitmap){
                    return bitmap.getByteCount();
                }
            };
        }

        public static SmartImageDownloader getInstance(){
            if (instance==null){
                synchronized (SmartImageDownloader.class){
                    if (instance==null){
                        instance = new SmartImageDownloader();
                    }
                }
            }
            return instance;
        }

        public void download(String url, ImageView imageView) {
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap!=null){
                imageView.setImageBitmap(bitmap);
                Log.e("hit cache!!!!!!", "hit cache!!!!!!");
                return;
            }
            BitmapDownloaderTask newTask = new BitmapDownloaderTask(imageView);
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
            private final WeakReference<ImageView> imageViewWeakReference;
            public String url;

            public BitmapDownloaderTask(ImageView imageView) {
                imageViewWeakReference = new WeakReference<ImageView>(imageView);
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

                if (imageViewWeakReference != null) {
                    ImageView imageView = imageViewWeakReference.get();
                    if(imageView==null){
                        Log.e("BitmapDownloaderTask", "imageView is empt!!!!!!!");
                        return;
                    }
                    imageView.setImageBitmap(bitmap);
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
