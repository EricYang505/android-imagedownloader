

package test.eric.com.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    GridLayoutManager layoutManager;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleId);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new ImageAdapter();
        mRecyclerView.setAdapter(adapter);
        ImageLoader.getInstance().loadList("kittens");
        ImageLoader.getInstance().addObserver(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * Callback method to be invoked when the RecyclerView has been scrolled. This will be
             * called after the scroll has completed.
             * <p/>
             * This callback will also be called if visible item range changes after a layout
             * calculation. In that case, dx and dy will be 0.
             *
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            boolean isLoading = false;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPostion = layoutManager.findLastVisibleItemPosition();
                if(!isLoading && lastPostion==ImageLoader.getInstance().getPhotos().size()-1){
                    isLoading = true;
//                    ImageLoader.getInstance().loadList(ImageLoader.getInstance().getPhotos().size(), 20);
                }
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ImageLoader.getInstance().deleteObserver(this);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        this.adapter.notifyDataSetChanged();
        isLoading = false;
    }

    public class ImageAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
            ImageLoader.getInstance().loadImage(ImageLoader.getInstance().getImageUrl(position), holder);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return ImageLoader.getInstance().getPhotos().size();
        }
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        WeakReference<AsyncTask> task;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageId);
        }
    }

}
