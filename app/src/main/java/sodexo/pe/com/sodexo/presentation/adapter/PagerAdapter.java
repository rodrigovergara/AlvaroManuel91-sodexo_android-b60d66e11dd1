package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import sodexo.pe.com.sodexo.presentation.fragment.intranet.PromoCommerceFragment;

/**
 * Created by RONALD on 14/10/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putString(PromoCommerceFragment.TITLE, "Promociones");
                bundle.putBoolean(PromoCommerceFragment.ISCOMMERCE, false);
                Fragment fragment = new PromoCommerceFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                bundle.putString(PromoCommerceFragment.TITLE, "Comercios");
                bundle.putBoolean(PromoCommerceFragment.ISCOMMERCE, true);
                Fragment fragment1 = new PromoCommerceFragment();
                fragment1.setArguments(bundle);
                return fragment1;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Promociones";
                break;
            case 1:
                title = "Comercios";
                break;
        }
        return title;
    }
}