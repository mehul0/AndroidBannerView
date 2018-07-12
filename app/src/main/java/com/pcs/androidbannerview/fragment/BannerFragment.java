package com.pcs.androidbannerview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pcs.androidbannerview.R;
import com.pcs.androidbannerview.adapter.BannerAdapter;


public class BannerFragment extends Fragment /*implements BannerAdapter.BannerListener*/ {
    public static String TAG = BannerFragment.class.getSimpleName();

    ViewPager viewPager;
    int images[] = {R.drawable.img_one, R.drawable.img_two, R.drawable.img_three, R.drawable.img_four, R.drawable.img_five, R.drawable.img_six};
    BannerAdapter myCustomPagerAdapter;
    ImageView imgLeftArrow, imgRightArrow;

    private OnFragmentInteractionListener mListener;

    public static BannerFragment getInstance() {
        BannerFragment fragment = new BannerFragment();
        return fragment;
    }

    public BannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        imgLeftArrow = view.findViewById(R.id.img_left);
        imgRightArrow = view.findViewById(R.id.img_right);


        myCustomPagerAdapter = new BannerAdapter(getActivity(), images/*,this*/);
        viewPager.setAdapter(myCustomPagerAdapter);
        toggleArrowVisibility( true, 0 == images.length - 1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                toggleArrowVisibility(i == 0, i == images.length - 1);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        imgLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.arrowScroll(ViewPager.FOCUS_LEFT);
            }
        });

        imgRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.arrowScroll(ViewPager.FOCUS_RIGHT);
            }
        });
    }

    public void toggleArrowVisibility(boolean isAtZeroIndex, boolean isAtLastIndex) {
        if (isAtZeroIndex)
            imgLeftArrow.setVisibility(View.INVISIBLE);
        else
            imgLeftArrow.setVisibility(View.VISIBLE);
        if (isAtLastIndex)
            imgRightArrow.setVisibility(View.INVISIBLE);
        else
            imgRightArrow.setVisibility(View.VISIBLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

  /*  @Override
    public void leftClick(int pos) {
        viewPager.arrowScroll(ViewPager.FOCUS_LEFT);
    }

    @Override
    public void rightClick(int pos) {
        viewPager.arrowScroll(ViewPager.FOCUS_RIGHT);
    }
*/
    public interface OnFragmentInteractionListener { }
}
