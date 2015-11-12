package com.example.android.imagedownloader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class RecycleViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EYImageDownloader imageDownloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        imageDownloader = EYImageDownloader.getInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

//        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ImageAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ImageAdapter extends RecyclerView.Adapter<ViewHolder> {

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.setText("Index:" + position);
            holder.imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon));
            imageDownloader.download(ImageData.URLS[position], holder);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return ImageData.URLS.length;
        }
    }
}

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
class ViewHolder extends RecyclerView.ViewHolder{
    WeakReference<AsyncTask> taskWeakReference;
    TextView textView;
    ImageView imageView;

    public ViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position==RecyclerView.NO_POSITION)
                    return;
                textView.setText("Clicked!");
            }
        });
        textView = (TextView) itemView.findViewById(R.id.listTextId);
        imageView = (ImageView) itemView.findViewById(R.id.listImageId);
    }
}


//A flexible view for providing a limited window into a large data set.
//
//        Glossary of terms:
//
//        Adapter: A subclass of RecyclerView.Adapter responsible for providing views that represent items in a data set.
//        Position: The position of a data item within an Adapter.
//        Index: The index of an attached child view as used in a call to getChildAt(int). Contrast with Position.
//        Binding: The process of preparing a child view to display data corresponding to a position within the adapter.
//        Recycle (view): A view previously used to display data for a specific adapter position may be placed in a cache for later reuse to display the same type of data again later. This can drastically improve performance by skipping initial layout inflation or construction.
//        Scrap (view): A child view that has entered into a temporarily detached state during layout. Scrap views may be reused without becoming fully detached from the parent RecyclerView, either unmodified if no rebinding is required or modified by the adapter if the view was considered dirty.
//        Dirty (view): A child view that must be rebound by the adapter before being displayed.
//        Positions in RecyclerView:
//
//        RecyclerView introduces an additional level of abstraction between the RecyclerView.Adapter and RecyclerView.LayoutManager to be able to detect data set changes in batches during a layout calculation. This saves LayoutManager from tracking adapter changes to calculate animations.
//        It also helps with performance because all view bindings happen at the same time and unnecessary bindings are avoided.
//
//        For this reason, there are two types of position related methods in RecyclerView:
//
//        layout position: Position of an item in the latest layout calculation. This is the position from the LayoutManager's perspective.
//        adapter position: Position of an item in the adapter. This is the position from the Adapter's perspective.

//        These two positions are the same except the time between dispatching adapter.notify* events and calculating the updated layout.
//
//        Methods that return or receive *LayoutPosition* use position as of the latest layout calculation (e.g. getLayoutPosition(), findViewHolderForLayoutPosition(int)). These positions include all changes until the last layout calculation. You can rely on these positions to be consistent with what
//        user is currently seeing on the screen. For example, if you have a list of items on the screen and user asks for the 5th element, you should use these methods as they'll match what user is seeing.
//
//        The other set of position related methods are in the form of *AdapterPosition*. (e.g. getAdapterPosition(), findViewHolderForAdapterPosition(int)) You should use these methods when you need to work with up-to-date adapter positions even if they may not have been reflected to layout yet.
//        For example, if you want to access the item in the adapter on a ViewHolder click, you should use getAdapterPosition(). Beware that these methods may not be able to calculate adapter positions if notifyDataSetChanged() has been called and new layout has not yet been calculated. For this reasons,
//        you should carefully handle NO_POSITION or null results from these methods.
//
//        When writing a RecyclerView.LayoutManager you almost always want to use layout positions whereas when writing an RecyclerView.Adapter, you probably want to use adapter positions.