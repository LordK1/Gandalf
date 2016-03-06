package com.k1.gandalf.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.k1.gandalf.R;

/**
 * this customized {@link ViewGroup} can handle child views is expanded or collapsed !
 * <p/>
 * Created by k1 on 2/24/16.
 */
public class ExpandableViewGroup extends ViewGroup {

    private static final String TAG = ExpandableViewGroup.class.getSimpleName();
    private int deviceWidth;
    private View child;
    private LinearLayout.LayoutParams childParams;

    public ExpandableViewGroup(Context context) {
        super(context);
        init(context);
    }

    public ExpandableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandableViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * initialize view and children within the display and width and height
     *
     * @param context
     */
    private void init(Context context) {
        inflate(context, R.layout.expandable_view_child_item, this);
        final Display display = ((WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        deviceWidth = deviceDisplay.x;
//        child = LayoutInflater.from(context).inflate(R.layout.expandable_view_child_item, null, false);
        child = new TextView(context);
        childParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        child.setLayoutParams(childParams);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout : " + changed + " l : " + l + " t : " + t + " r: " + r + " b: "
                + b + " child count : " + getChildCount());

        for (int i = 0; i < 3; i++) {
            final TextView textView = new TextView(getContext());
            textView.setText("Child : " + i);
            addView(textView);
        }
    }
}
