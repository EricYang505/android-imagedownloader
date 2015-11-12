/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.imagedownloader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ImageListActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private final EYImageDownloader imageDownloader = EYImageDownloader.getInstance();//new ImageDownloader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagelist);
        ListView listView = (ListView) findViewById(R.id.myListId);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        listView.setAdapter(new ImageAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position); //related to public String getItem(int position) {}
                //id related to public long getItemId(int position) {}
            }
        });
    }


    private class ImageAdapter extends BaseAdapter {

        public int getCount() {
            return ImageData.URLS.length;
        }

        public int getItemViewType(int position) {
            return position%2;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public String getItem(int position) {
            return ImageData.URLS[position];
        }

        public long getItemId(int position) {
            return ImageData.URLS[position].hashCode();
        }

        public View getView(int position, View view, ViewGroup parent) {
            if (position%2==0) {
                ViewHolder viewHolder;
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.list_item1, null);
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                viewHolder.textView.setText("Index:" + position);
                viewHolder.imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon));
                imageDownloader.download(ImageData.URLS[position], viewHolder);
            }else {
                ViewHolder2 viewHolder;
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.list_item2, null);
                    viewHolder = new ViewHolder2();
                    viewHolder.textView = (TextView) view.findViewById(R.id.listTextId2);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder2) view.getTag();
                }
                viewHolder.textView.setText("Index:" + position);
            }
            return view;
        }
    }

    private class ViewHolder2{
        TextView textView;
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        ImageDownloader.Mode mode = ImageDownloader.Mode.NO_ASYNC_TASK;

        if (checkedId == R.id.correctButton) {
            mode = ImageDownloader.Mode.CORRECT;
        }else if (checkedId == R.id.randomButton) {
            mode = ImageDownloader.Mode.NO_DOWNLOADED_DRAWABLE;
        }

//        imageDownloader.setMode(mode);
    }

}
