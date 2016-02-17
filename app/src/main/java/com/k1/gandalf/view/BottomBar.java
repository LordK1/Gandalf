package com.k1.gandalf.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.k1.gandalf.R;


/**
 * Created by k1 on 2/16/16.
 */
public class BottomBar extends LinearLayout {


    private View root;
    private TextView dashboardTextView;
    private TextView onSaleTextView;
    private TextView myFlyersTextView;
    private TextView flyersTextView;

    public BottomBar(Context context) {
        super(context, null);
        init(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * make bottom bar
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        root = inflate(context, R.layout.bottom_bar, this);
        dashboardTextView = (TextView) root.findViewById(R.id.txt_view_dashboard);
        onSaleTextView = (TextView) root.findViewById(R.id.txt_view_sale);
        myFlyersTextView = (TextView) root.findViewById(R.id.txt_view_my_flyers);
        flyersTextView = (TextView) root.findViewById(R.id.txt_view_flyers);
    }
}
