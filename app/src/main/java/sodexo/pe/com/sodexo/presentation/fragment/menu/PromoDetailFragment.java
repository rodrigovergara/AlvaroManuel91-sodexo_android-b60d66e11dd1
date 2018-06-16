package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.WishListView;
import sodexo.pe.com.sodexo.presentation.presenter.WishListPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.WishListPresenterImplement;

public class PromoDetailFragment extends MenuBaseFragment implements WishListView {

    @BindView(R.id.iv_banner_promo)
    ImageView ivBannerPromo;
    @BindView(R.id.iv_logo_promo)
    ImageView ivLogoPromo;
    @BindView(R.id.tv_name_promo)
    TextView tvPromoName;

    @BindView(R.id.tv_atention)
    TextView tvAtention;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_terms)
    TextView tvTerms;

    @BindView(R.id.btn_download_coupon_promo)
    Button downoadCuponButton;
    private ProgressCustomDialog dialog;
    public static String PROMO = "PROMO";
    private WishListPresenter presenter;
    PromoEntity currentPromo;
    public static String IS_WISHLIST = "wishlist";
    public boolean isWishList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promo_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressCustomDialog();
        presenter = new WishListPresenterImplement(this);
        if (getArguments() != null) {
            currentPromo =  getArguments().getParcelable(PROMO);
            showDataPromo(currentPromo);
        }
    }

    private void showDataPromo(PromoEntity promo) {
        Glide.with(this).load(promo.getPhoto()).into(ivBannerPromo);
        Glide.with(this).load(promo.getImageLogo()).into(ivLogoPromo);
        tvPromoName.setText(promo.getName());
        tvAtention.setText(promo.getAtention());
        tvAddress.setText(promo.getAddress());
        tvPhoneNumber.setText(promo.getPhoneNumber());
        tvTerms.setText(promo.getTerms());
    }

    @OnClick(R.id.btn_download_coupon_promo)
    public void downloadCupon(){
        Log.d("wat", currentPromo.getDescription());
        if((currentPromo.getCuponUrl()!=null)&&(!currentPromo.getCuponUrl().equals(""))){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentPromo.getCuponUrl()));
            startActivity(browserIntent);
        }
        else{
            AlertUtil.showMessageAccept(getContext(),"Descarga","No hay cupón disponible para esta promoción.","Aceptar",null);
        }
    }

    @OnClick(R.id.iv_wishlist)
    public void addToWishList() {
        if (getArguments() != null) {
            isWishList = getArguments().getBoolean(IS_WISHLIST, false);
            if(isWishList) {
                AlertUtil.showMessageAcceptNegative(getContext(), null, "¿Deseas eliminar esta promoción del wishlist?", "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.deletePromoToWishList((PromoEntity) getArguments().getParcelable(PROMO));
                        dialogInterface.dismiss();
                    }
                }, "No", null);
            } else {
                AlertUtil.showMessageAcceptNegative(getContext(), null, "¿Deseas agregar esta promoción al wishlist?", "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.addPromoToWishlist((PromoEntity) getArguments().getParcelable(PROMO));
                        dialogInterface.dismiss();
                    }
                }, "No", null);

            }
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
        AlertUtil.showMessageAccept(getContext(), "", "Se ha guardado la promoción correctamente", "Aceptar", null);
    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }

    @Override
    public void showCommerces(List<CommerceEntity> commerce) {
        // null
    }

    @Override
    public void showPromos(List<PromoEntity> promo) {
        // null
    }

    @Override
    public void showSuccessDelete(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }
}
