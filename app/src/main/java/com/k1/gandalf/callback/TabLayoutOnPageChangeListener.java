package com.k1.gandalf.callback;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.k1.gandalf.MainActivity;

import java.lang.ref.WeakReference;

/**
 * A {@link ViewPager.OnPageChangeListener} class which contains the
 * necessary calls back to the provided {@link TabLayout} so that the tab position is
 * kept in sync.
 * <p/>
 * <p>This class stores the provided TabLayout weakly, meaning that you can use
 * {@link ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)
 * addOnPageChangeListener(OnPageChangeListener)} without removing the listener and
 * not cause a leak.
 */
public class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private final WeakReference<TabLayout> mTabLayoutRef;
    private final TabLayout.OnTabSelectedListener mOnTabSelectedListener;
    private int mPreviousScrollState;
    private int mScrollState;
    private TabLayout.Tab mSelectedTab;

    public TabLayoutOnPageChangeListener(TabLayout mTabLayout, TabLayout.OnTabSelectedListener onTabSelectedListener) {
        mOnTabSelectedListener = onTabSelectedListener;
        mTabLayoutRef = new WeakReference<TabLayout>(mTabLayout);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

        mPreviousScrollState = mScrollState;
        mScrollState = state;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(MainActivity.TAG, "onPageScrolled : " + position
                + " positionOffset : " + positionOffset
                + " positionOffsetPixels :" + positionOffsetPixels);
        final TabLayout tabLayout = mTabLayoutRef.get();
        if (tabLayout != null) {
            // Update the scroll position, only update the text selection if we're being
            // dragged (or we're settling after a drag)
            final boolean updateText = (mScrollState == ViewPager.SCROLL_STATE_DRAGGING)
                    || (mScrollState == ViewPager.SCROLL_STATE_SETTLING
                    && mPreviousScrollState == ViewPager.SCROLL_STATE_DRAGGING);
            tabLayout.setScrollPosition(position, 0, updateText);
        }
    }

    @Override
    public void onPageSelected(int position) {
        final TabLayout tabLayout = mTabLayoutRef.get();
        if (tabLayout != null && tabLayout.getSelectedTabPosition() != position) {
// Select the tab, only updating the indicator if we're not being dragged/settled
            // (since onPageScrolled will handle that).
//                mTabLayout.setScrollPosition(position, 0, true);
            selectTab(tabLayout.getTabAt(position), mScrollState == ViewPager.SCROLL_STATE_IDLE);
            final int tabCount = tabLayout.getChildCount();
            if (position < tabCount && !mTabLayoutRef.get().getChildAt(position).isSelected()) {
                for (int i = 0; i < tabCount; i++) {
                    final View child = tabLayout.getChildAt(i);
                    Log.i(MainActivity.TAG, "child : " + i + " selected : " + (i == position));
                    child.setSelected(i == position);
                }
            }
        }
    }

    private void selectTab(TabLayout.Tab tabAt, boolean updateIndicator) {
        if (mSelectedTab == tabAt) {
            if (mSelectedTab != null) {
                if (mOnTabSelectedListener != null) {
                    mOnTabSelectedListener.onTabReselected(mSelectedTab);
                }
                animateToTab(tabAt.getPosition());
            }
        } else {
            if (updateIndicator) {
                final int newPosition = tabAt != null ? tabAt.getPosition() : TabLayout.Tab.INVALID_POSITION;
                if (newPosition != TabLayout.Tab.INVALID_POSITION) {
                    setSelectedTabView(newPosition);
                }
                if (mSelectedTab == null || mSelectedTab.getPosition() == TabLayout.Tab.INVALID_POSITION
                        && newPosition != TabLayout.Tab.INVALID_POSITION
                        ) {
                    // If we don't currently have a tab, just draw the indicator
                    mTabLayoutRef.get().setScrollPosition(newPosition, 0f, true);
                } else {
                    animateToTab(newPosition);
                }
            }
            if (mSelectedTab != null && mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabUnselected(mSelectedTab);
            }
            mSelectedTab = tabAt;
            if (mSelectedTab != null && mOnTabSelectedListener != null) {
                mOnTabSelectedListener.onTabSelected(mSelectedTab);
            }
        }
    }

    private void setSelectedTabView(int position) {
        final int tabCount = mTabLayoutRef.get().getTabCount();
        if (position < tabCount && !mTabLayoutRef.get().getChildAt(position).isSelected()) {
            for (int i = 0; i < tabCount; i++) {
                final View child = mTabLayoutRef.get().getChildAt(i);
                child.setSelected(i == position);

            }
        }

    }

    private void animateToTab(int newPosition) {
        if (newPosition == TabLayout.Tab.INVALID_POSITION) {
            return;
        }
        if (mTabLayoutRef.get().getWindowToken() == null ||
                !ViewCompat.isLaidOut(mTabLayoutRef.get())
                ) {
            // If we don't have a window token, or we haven't been laid out yet just draw the new
            // position now
            mTabLayoutRef.get().setScrollPosition(newPosition, 0f, true);
            return;
        }
    }
}
