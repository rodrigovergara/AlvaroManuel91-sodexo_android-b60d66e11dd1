package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.presentation.adapter.RVCommerceAdapter;
import sodexo.pe.com.sodexo.presentation.interfaces.CommerceFView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.presenter.CommerceFPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.CommerceFPresenterImplement;

public class CommerceFragment extends MenuBaseFragment implements CommerceFView, SearchView.OnQueryTextListener{
    @BindView(R.id.rv_commerce)
    RecyclerView rvCommerce;

    @BindView(R.id.commerce_search_view)
    SearchView commerceSearchView;

    RVCommerceAdapter adapter;
    private MainView mainView;
    private CountDownTimer queryCounter;
    public static final String COMMERCES = "commerces";

    CommerceFPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comerce, container, false);
        ButterKnife.bind(this, view);
        commerceSearchView.setIconifiedByDefault(false);
        commerceSearchView.setOnQueryTextListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            this.mainView = (MainView) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            addCommerces(getArguments().<CommerceEntity>getParcelableArrayList(COMMERCES));
        }
        presenter = new CommerceFPresenterImplement(this);
    }

    private void addCommerces(List<CommerceEntity> commerces) {
        adapter = new RVCommerceAdapter(mainView);
        adapter.addCommerces(commerces);
        rvCommerce.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        rvCommerce.setAdapter(adapter);
    }

    @Override
    public void showFilterCommerce(List<CommerceEntity> commerceEntities) {
        boolean filtering = commerceSearchView.getQuery().length()>0;
        adapter.showFilterCommerce(filtering,commerceEntities);
        mainView.hideLoading();
    }

    @Override
    public void showFilterError(String message) {
        mainView.showError(message);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.showFilterCommerce(query);
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
                presenter.showFilterCommerce(newText);
                mainView.showLoading();
            }
        };

        queryCounter.start();
        return true;
    }
}
