package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.adapter.RVCommerceWishListAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.RVPromoWishListAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.WishListView;
import sodexo.pe.com.sodexo.presentation.presenter.WishListPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.WishListPresenterImplement;

public class PromoCommerceFragment extends Fragment implements WishListView{

    public static String TITLE = "title";
    public static String ISCOMMERCE = "COMMERCE";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    private WishListPresenter presenter;
    private ProgressCustomDialog dialog;
    private MainView mainView;
    private RVCommerceWishListAdapter commerceAdapter;
    private RVPromoWishListAdapter promoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promo_commerce, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        promoAdapter = new RVPromoWishListAdapter(mainView, this);
        commerceAdapter = new RVCommerceWishListAdapter(mainView, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new WishListPresenterImplement(this);
        dialog = new ProgressCustomDialog();
        if (getArguments() != null) {
            tvTitle.setText(getArguments().getString(TITLE));
            if (getArguments().getBoolean(ISCOMMERCE)){
                presenter.getCommerceWishList();
            } else {
                presenter.getPromoWishList();
            }
        }
    }

    public void restartList() {
        if (getArguments().getBoolean(ISCOMMERCE)){
            presenter.getCommerceWishList();
        } else {
            presenter.getPromoWishList();
        }
    }

    @Override
    public void showLoading() {
        if (dialog != null) {
            dialog.show(getFragmentManager(), ProgressCustomDialog.class.getName());
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismissAllowingStateLoss();
        }
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }

    @Override
    public void showCommerces(List<CommerceEntity> commerce) {
        commerceAdapter.addCommerceList(commerce);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        rv.setAdapter(commerceAdapter);
    }

    @Override
    public void showPromos(List<PromoEntity> promo) {
        promoAdapter.addPromoList(promo);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        rv.setAdapter(promoAdapter);
    }

    @Override
    public void showSuccessDelete(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().onBackPressed();
                dialogInterface.dismiss();
            }
        });
    }
}
