package com.k1.gandalf;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.k1.gandalf.view.RTLViewPager;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadgeAdder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;

import java.text.DateFormatSymbols;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = FragmentActivity.class.getSimpleName();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    String[] titles = new String[]{

            "صحفه اول",
            "صحفه دوم",
            "صحفه سوم",
    };

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private RTLViewPager mViewPager;
    private String[] months;
    private TabLayout mTabLayout;
    private FuckinPagerAdapter mFuckinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        months = DateFormatSymbols.getInstance().getMonths();
        // Create the mFuckinAdapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections mFuckinAdapter.
        mViewPager = (RTLViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
        mFuckinAdapter = new FuckinPagerAdapter();
        mViewPager.setAdapter(mFuckinAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
        mTabLayout.removeAllTabs();
        for (int i = 0; i < mFuckinAdapter.getCount(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled : " + position
                        + " positionOffset : " + positionOffset
                        + " positionOffsetPixels :" + positionOffsetPixels);
                mTabLayout.setScrollPosition(position, positionOffsetPixels, true);
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        new ActionItemBadgeAdder().act(this)
                .menu(menu)
                .title(R.string.define_font_fontawesome)
                .itemDetails(0, 02, 1)
                .showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                .add(0);
        ActionItemBadge.update(this, menu.findItem(R.id.badge),
                FontAwesome.Icon.faw_android,
                0, 0);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    /**
     * Fuckin PagerAdapter
     */
    public class FuckinPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object.equals(view.getTag());
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            final String item = titles[position];
            final TextView text = new TextView(container.getContext());
            text.setGravity(Gravity.CENTER);
            text.setBackgroundColor(Color.WHITE);
            text.setTextColor(Color.BLACK);
            text.setTextSize(20);
            text.setText(item);
            container.addView(text, MATCH_PARENT, MATCH_PARENT);
            text.setTag(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(container.findViewWithTag(object));
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return months.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return months[position];
        }
    }
}
