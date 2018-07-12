package com.pcs.androidbannerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pcs.androidbannerview.fragment.BannerFragment;

public class MainActivity extends AppCompatActivity implements BannerFragment.OnFragmentInteractionListener {

    BannerFragment bannerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadBannerFragment();
    }

    public void loadFragment(Fragment fragmentContext, String tag, String backStack) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragmentContext, tag)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(backStack)
                .commit();
    }

    /** load : iFragment **/
    public void loadBannerFragment() {
        if (bannerFragment == null)
            bannerFragment = new BannerFragment();

        loadFragment(bannerFragment, BannerFragment.TAG, BannerFragment.TAG);
    }
}
