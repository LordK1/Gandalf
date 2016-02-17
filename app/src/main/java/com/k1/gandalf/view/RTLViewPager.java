package com.k1.gandalf.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.k1.gandalf.MainApplication;

import java.util.Map;

/**
 * this is customized RTL support ViewPager
 * FIXME: check everything must be reversed correctly
 * Created by k1 on 2/10/16.
 */
public class RTLViewPager extends ViewPager {

    private static final String TAG = RTLViewPager.class.getSimpleName();

    @NonNull
    private final Map<OnPageChangeListener, ReverseOnPageChangeListener> reverseOnPageChangeListeners;

    @Nullable
    private DataSetObserver dataSetObserver;

    private boolean suppressOnPageChangeListeners;

    public RTLViewPager(Context context) {
        super(context);
        reverseOnPageChangeListeners = new ArrayMap<>(1);
    }

    public RTLViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        reverseOnPageChangeListeners = new ArrayMap<>(1);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerRtlDataSetObserver(super.getAdapter());
    }

    @Override
    protected void onDetachedFromWindow() {
        unregisterRtlDataSetObserver();
        super.onDetachedFromWindow();
    }

    private void registerRtlDataSetObserver(PagerAdapter adapter) {
        if (adapter instanceof ReverseAdapter && dataSetObserver == null) {
            dataSetObserver = new RevalidateIndicesOnContentChange((ReverseAdapter) adapter);
            adapter.registerDataSetObserver(dataSetObserver);
            ((ReverseAdapter) adapter).revalidateIndices();
        }
    }

    private void unregisterRtlDataSetObserver() {
        final PagerAdapter adapter = super.getAdapter();

        if (adapter instanceof ReverseAdapter && dataSetObserver != null) {
            adapter.unregisterDataSetObserver(dataSetObserver);
            dataSetObserver = null;
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(convert(item), smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(convert(item));
    }

    @Override
    public int getCurrentItem() {
        return convert(super.getCurrentItem());
    }

    private int convert(int position) {
        if (position >= 0 && isRtl()) {
            return getAdapter() == null ? 0 : getAdapter().getCount() - position - 1;
        } else {
            return position;
        }
    }

    @Nullable
    @Override
    public PagerAdapter getAdapter() {
        final PagerAdapter adapter = super.getAdapter();
        return adapter instanceof ReverseAdapter ? ((ReverseAdapter) adapter).getInnerAdapter() : adapter;
    }

    @Override
    public void fakeDragBy(float xOffset) {
        super.fakeDragBy(isRtl() ? xOffset : -xOffset);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        unregisterRtlDataSetObserver();

        final boolean rtlReady = adapter != null && isRtl();
        if (rtlReady) {
            adapter = new ReverseAdapter(adapter);
            registerRtlDataSetObserver(adapter);
        }
        super.setAdapter(adapter);
        if (rtlReady) {
            setCurrentItemWithoutNotification(0);
        }
    }

    private void setCurrentItemWithoutNotification(int index) {
        suppressOnPageChangeListeners = true;
        setCurrentItem(index, false);
        suppressOnPageChangeListeners = false;
    }

    /**
     * Check the fuckin RTL direction
     *
     * @return
     */
    protected boolean isRtl() {
        return MainApplication.isLocaleFa(getContext());
    }

    @Override
    public void addOnPageChangeListener(@NonNull OnPageChangeListener listener) {
        if (isRtl()) {
            final ReverseOnPageChangeListener reverseListener = new ReverseOnPageChangeListener(listener);
            reverseOnPageChangeListeners.put(listener, reverseListener);
            listener = reverseListener;
        }
        super.addOnPageChangeListener(listener);
    }

    @Override
    public void removeOnPageChangeListener(@NonNull OnPageChangeListener listener) {
        if (isRtl()) {
            listener = reverseOnPageChangeListeners.remove(listener);
        }
        super.removeOnPageChangeListener(listener);
    }


    private class ReverseAdapter extends PagerAdapterWrapper {

        private int lastCount;

        public ReverseAdapter(@NonNull PagerAdapter adapter) {
            super(adapter);
            lastCount = adapter.getCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(reverse(position));
        }

        @Override
        public float getPageWidth(int position) {
            return super.getPageWidth(reverse(position));
        }

        @Override
        public int getItemPosition(Object object) {
            final int itemPosition = super.getItemPosition(object);
            return itemPosition < 0 ? itemPosition : reverse(itemPosition);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, reverse(position));
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, reverse(position), object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, lastCount - position - 1, object);
        }

        private int reverse(int position) {
            int newPosition = getCount() - position - 1;
            return newPosition;
        }

        /**
         * this method call every time pager, changes paging
         * FIXME:check the fuckin results
         */
        private void revalidateIndices() {

            final int newCount = getCount();
            Log.i(TAG, "revalidateIndices newCount : " + newCount + " lastCount : " + lastCount);
            if (newCount != lastCount) {
                setCurrentItemWithoutNotification(Math.max(0, lastCount - 1));
                lastCount = newCount;
            }
        }
    }

    private static class RevalidateIndicesOnContentChange extends DataSetObserver {
        @NonNull
        private final ReverseAdapter adapter;

        private RevalidateIndicesOnContentChange(@NonNull ReverseAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onChanged() {
            super.onChanged();
            adapter.revalidateIndices();
        }
    }

    private class ReverseOnPageChangeListener implements OnPageChangeListener {

        @NonNull
        private final OnPageChangeListener original;

        private ReverseOnPageChangeListener(@NonNull OnPageChangeListener original) {
            this.original = original;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (!suppressOnPageChangeListeners) {
                original.onPageScrolled(reverse(position), 1f - positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (!suppressOnPageChangeListeners) {
                original.onPageSelected(reverse(position));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (!suppressOnPageChangeListeners) {
                original.onPageScrollStateChanged(state);
            }
        }

        private int reverse(int position) {
            final PagerAdapter adapter = getAdapter();
            return adapter == null ? position : adapter.getCount() - position - 1;
        }
    }
}
