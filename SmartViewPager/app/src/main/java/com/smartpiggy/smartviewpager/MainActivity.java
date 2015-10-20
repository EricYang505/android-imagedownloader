package com.smartpiggy.smartviewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerId);
        viewPager.setAdapter(new SmartPagerAdapter(getSupportFragmentManager()));
//        viewPager.setAdapter(new SmartStatePagerAdapter(getSupportFragmentManager()));

    }

    private class SmartStatePagerAdapter extends FragmentStatePagerAdapter {

        public SmartStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            DummyFragment fragment = new DummyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DummyFragment.position_key, position);
            fragment.setArguments(bundle);
            return fragment;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return ImageData.URLS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Image " + (position);
        }
    }

    private class SmartPagerAdapter extends FragmentPagerAdapter {

        public SmartPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return 3;
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            DummyFragment fragment = new DummyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DummyFragment.position_key, position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Image " + (position);
        }
    }

    public static class DummyFragment extends Fragment{
        public static String position_key = "position_key";
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_dummy, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageId1);
            Bundle bundle = getArguments();
            Log.e("Download", "Download"+bundle.getInt(position_key));
            SmartImageDownloader.getInstance().download(ImageData.URLS[bundle.getInt(position_key)], imageView);
            return view;
        }
    }
}
