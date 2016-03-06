package com.k1.gandalf;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * This is simple sample {@link AppCompatActivity}
 * that used as Detail page or Content of stuff like that contains
 * the some vertical scrollable information with some effects on toolbar and some parts of body
 * <p/>
 * <p/>
 * Created by k1 on 2/28/16.
 */
public class ScrollingActivity extends AppCompatActivity {

    public static final String TAG = ScrollingActivity.class.getSimpleName();
    private NestedScrollView mScrollView;
    private FloatingActionButton mFab;
    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private AppBarLayout mAppBarLayout;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
        setContentView(R.layout.activity_scrolling);

//        set an exit transitions
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setExitTransition(new Explode());
//        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Detail of Something Fuckable !!!");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                Log.i(TAG, "onSharedElementStart : " + sharedElementNames.size());
            }
        });
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mToolbarTitle = (TextView) findViewById(R.id.scrolling_toolbar_text_view);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mScrollView = (NestedScrollView) findViewById(R.id.scrolling_nested_scroll_view);
        mScrollView.setNestedScrollingEnabled(true);
        mScrollView.setFillViewport(true);
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {


            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i(TAG, " scrollX : " + scrollX
                                + " scrollY : " + scrollY
                                + " oldScrollX : " + oldScrollX
                                + " oldScrollY : " + oldScrollY
                );
                mAppBarLayout.animate().translationY(scrollY).alpha(0.5f).start();
//                mToolbar.setTranslationY(scrollY);
                mToolbarTitle.setTranslationY(scrollY);
                if (oldScrollY > scrollY) {
                    Log.i(TAG, "DOWN WARD !!!");
                } else if (scrollY > oldScrollY) {
                    Log.i(TAG, "UP Ward !!!");
                }
            }
        });

        CoordinatorLayout mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.scrolling_coordinator_layout);
        mCoordinatorLayout.setNestedScrollingEnabled(true);
        /*mCoordinatorLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i(TAG, "v : " + v.getId()
                                + " scrollX : " + scrollX
                                + " scrollY : " + scrollY
                                + " oldScrollX : " + oldScrollX
                                + " oldScrollY : " + oldScrollY
                );
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fuck:
                Toast.makeText(this, "You want Fuck me, Ok !?! \n then Go FUCK YOURSELG ",
                        Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
