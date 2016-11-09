/*
 * *
 *   * Copyright 2015 Accela, Inc.
 *   *
 *   * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 *   * use, copy, modify, and distribute this software in source code or binary
 *   * form for use in connection with the web services and APIs provided by
 *   * Accela.
 *   *
 *   * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 *   * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *   * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *   * DEALINGS IN THE SOFTWARE.
 *   *
 *
 */

package test.eric.com.myapplication;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by eyang on 10/24/16.
 */
public class ImageLoader extends Observable{
    private static String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_k" +
            "ey=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&text=kitten";
    private static ImageLoader instance;
    private LruCache<String, Bitmap> bitmapCache;
    private List<Photo> photos = new ArrayList<>();

    private ImageLoader(){
        final int cacheSize = 4 * 1024 * 1024; // 4MiB
        bitmapCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap){
                return bitmap.getByteCount();
            }
        };
    }

    public List<Photo> getPhotos(){
        return photos;
    }

    public static ImageLoader getInstance(){
        if (instance==null){
            synchronized (ImageLoader.class){
                if (instance==null){
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    public void loadList(String keyword){
        if (photos.size()==0){
            ListAsyncTask task = new ListAsyncTask();
//            StringBuilder sb = new StringBuilder(url);
//            sb.append(keyword);
            task.execute(url);
        }
    }

    public String getImageUrl(int position){
        StringBuilder sb = new StringBuilder();
        sb.append("http://farm");
        sb.append(photos.get(position).getFarm());
        sb.append(".static.flickr.com/");
        sb.append(photos.get(position).getServer());
        sb.append("/");
        sb.append(photos.get(position).getId()).append("_").append(photos.get(position).getSecret());
        sb.append(".jpg");
        String str = sb.toString();
        return str;
    }

    class ListAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return HttpRequest.getJSON(url);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result!=null){
                try {
                    JSONObject returnValue = new JSONObject(result);
                    JSONObject object = returnValue.getJSONObject("photos");
                    String jsonArray = object.getString("photo");
                    Type listType = new TypeToken<ArrayList<Photo>>(){}.getType();
                    photos = new Gson().fromJson(jsonArray, listType);
                    Log.d(photos.toString(), String.valueOf(photos.size()));
                    setChanged();
                    notifyObservers();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }




    public void loadImage(String url, MainActivity.MyViewHolder viewHolder){
        if(this.bitmapCache.get(url)!=null){
            viewHolder.imageView.setImageBitmap(this.bitmapCache.get(url));
            Log.d("loadImage", "Hit cache: " + url);
            return;
        }
        if (viewHolder.task!=null && viewHolder.task.get()!=null){
            viewHolder.task.get().cancel(true);
            Log.d("loadImage", "Cancel task: " + url);
        }
        ImageAsyncTask task = new ImageAsyncTask(viewHolder);
        viewHolder.task = new WeakReference<AsyncTask>(task);
        task.execute(url);
    }

    class ImageAsyncTask extends AsyncTask<String, Void, Bitmap>{

        private WeakReference<MainActivity.MyViewHolder> mViewHolder;
        private String mUrl;

        public ImageAsyncTask(MainActivity.MyViewHolder viewHolder){
            this.mViewHolder = new WeakReference<MainActivity.MyViewHolder>(viewHolder);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            mUrl = params[0];
            return HttpRequest.downloadBitmap(mUrl);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if(result!=null && this.mViewHolder.get()!=null){
                MainActivity.MyViewHolder viewHolder = mViewHolder.get();
                if (viewHolder.task!=null && viewHolder.task.get()==this) {
                    this.mViewHolder.get().imageView.setImageBitmap(result);
                }else{
                    Log.d("ImageAsyncTask", "Not same task!");
                }
                this.mViewHolder.get().task = null;
                bitmapCache.put(mUrl, result);
            }
        }
    }



}
