package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.adapter.PagerAdapter;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishListFragment extends Fragment {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private  MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        ButterKnife.bind(this, view);
        tabLayout.addTab(tabLayout.newTab().setText("Comercios"));
        tabLayout.addTab(tabLayout.newTab().setText("Promociones"));
        tabLayout.setBackgroundResource(R.color.colorAccent);
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        viewpager.setAdapter(new PagerAdapter(getChildFragmentManager(), 2));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }
}
