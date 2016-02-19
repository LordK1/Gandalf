package com.k1.gandalf.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.k1.gandalf.R;

/**
 * Created by k1 on 2/19/16.
 */
public class TypefaceTextView extends TextView {

    public static final String TAG = TypefaceTextView.class.getSimpleName();
    private Typeface mDefaultTypeface;
    private Typeface mDefinedTypeface;

    public TypefaceTextView(Context context) {
        super(context);
        init(context, null);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mDefaultTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Oswald-Stencbab.ttf");
        if (attrs != null) {
            TypedArray array = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.TypefaceTextView,
                    0, 0
            );
            try {
                final String typefaceString = array.getString(R.styleable.TypefaceTextView_typeface);
                mDefinedTypeface = Typeface.createFromAsset(context.getAssets(), typefaceString);
                Log.i(TAG, "typeFaceString : " + typefaceString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mDefinedTypeface != null) {
            this.setTypeface(mDefinedTypeface);
        } else {
            this.setTypeface(mDefaultTypeface);
        }
    }
}
