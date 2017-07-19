package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.adapter.RVPromoAdapter;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.PromoFView;
import sodexo.pe.com.sodexo.presentation.presenter.CommerceFPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.PromoFPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.PromoFPresenterImplement;

public class PromoFragment extends MenuBaseFragment implements PromoFView,SearchView.OnQueryTextListener{
    public static final String PROMOS = "promos";
    @BindView(R.id.rv_promo)
    RecyclerView rvPromo;
    private RVPromoAdapter adapter;
    PromoFPresenter presenter;

    @BindView(R.id.promo_search_view)
    SearchView promoSearchView;

    private CountDownTimer queryCounter;

    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promo, container, false);
        ButterKnife.bind(this, view);
        promoSearchView.setIconifiedByDefault(false);
        promoSearchView.setOnQueryTextListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof  MainView) {
            mainView = (MainView) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            addPromos(getArguments().<PromoEntity>getParcelableArrayList(PROMOS));
        }
        presenter = new PromoFPresenterImplement(this);
    }

    private void addPromos(List<PromoEntity> promos) {
        adapter = new RVPromoAdapter(mainView);
        adapter.addPromoList(promos);
        rvPromo.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        rvPromo.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.showFilterPromo(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        if(queryCounter!=null)
            queryCounter.cancel();
        queryCounter = new CountDownTimer(750,250) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                presenter.showFilterPromo(newText);
                mainView.showLoading();
            }
        };

        queryCounter.start();
        return true;
    }

    @Override
    public void showFilterPromo(List<PromoEntity> promoEntities) {
        boolean filtering = promoSearchView.getQuery().length()>0;
        adapter.showFilterPromo(filtering,promoEntities);
        mainView.hideLoading();
    }

    @Override
    public void showFilterError(String message) {
        mainView.showError(message);
    }
}
