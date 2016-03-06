package com.k1.gandalf.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.johnkil.print.PrintDrawable;
import com.joanzapata.iconify.widget.IconTextView;
import com.k1.gandalf.R;
import com.k1.gandalf.ScrollingActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;
    private ImageView imageView;
    private TextView labelTextView;
    private IconTextView printView;


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

        imageView = (ImageView) rootView.findViewById(R.id.placeholder_image_view);
        labelTextView = (TextView) rootView.findViewById(R.id.section_label);
        labelTextView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        printView = (IconTextView) rootView.findViewById(R.id.main_icon_text_view);
        printView.setOnClickListener(new OnTextClickListener());
        /*PrintView printView = (PrintView) rootView.findViewById(R.id.main_print_view);
        printView.setIconFont(Typeface.createFromAsset(getContext().getAssets(), "fonts/Flaticon.ttf"));
        printView.setIconColorRes(R.color.colorAccent);
        printView.setIconSizeDp(100);
        printView.setIconTextRes(R.string.flaticonـarrow);*/

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.main_pager);
        viewPager.setAdapter(new InnerPagerAdapter());
        return rootView;
    }

    /**
     * this is little fuckin pretty inner item pager
     */
    private class InnerPagerAdapter extends PagerAdapter {

        public InnerPagerAdapter() {
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            context = container.getContext();
            TextView textView = new TextView(context);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    new PrintDrawable.Builder(context)
                            .iconSizeDp(30)
                            .iconColorRes(R.color.colorAccent)
                            .iconFont(Typeface.createFromAsset(context.getAssets(), "fonts/Flaticon.ttf"))
                            .iconTextRes(R.string.flaticonـtag)
                            .build()
                    , null, null, null
            );
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "منوی : " + (position + 1);
        }
    }

    /**
     * When On Text clicked, must go to {@link ScrollingActivity}
     */
    private class OnTextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            /**
             * For the fuckin explode transitions animations
             * Start an activity using transitions
             * */
            final Intent intent = new Intent(getActivity(), ScrollingActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<ImageView, String> imageViewStringPair =
                            Pair.create(imageView, imageView.getTransitionName());
                    Pair<TextView, String> labelPair = Pair.create(labelTextView,
                            labelTextView.getTransitionName());


                    ActivityOptionsCompat optionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    getActivity(),
                                    imageView,
                                    getString(R.string.transition_name_title));
                    getActivity().startActivity(
                            intent,
                            optionsCompat.toBundle()
                    );
                }
            }
        }
    }
}
