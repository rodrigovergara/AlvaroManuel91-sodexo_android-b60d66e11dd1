package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.WishListView;
import sodexo.pe.com.sodexo.presentation.presenter.WishListPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.WishListPresenterImplement;

public class LocalFragment extends MenuBaseFragment implements WishListView, OnMapReadyCallback {
    @BindView(R.id.fl_map)
    FrameLayout frameLayout;
    @BindView(R.id.iv_local)
    ImageView ivLocal;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;


    @BindView(R.id.tv_name_comerce)
    TextView tvComerceName;
    @BindView(R.id.tv_atention)
    TextView tvAtention;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;

    private GoogleMap googleMap;
    public static final String COMMERCE = "commerce";
    private WishListPresenter presenter;
    private CommerceEntity commerceEntity;
    private ProgressCustomDialog dialog;
    public static String IS_WISHLIST = "wishlist";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressCustomDialog();
        presenter = new WishListPresenterImplement(this);
        initializeMap();
    }

    private void showDataCommerce(CommerceEntity commerce) {
        commerceEntity = commerce;
        Glide.with(this).load(commerce.getBanner()).into(ivLocal);
        Glide.with(this).load(commerce.getLogo()).into(ivLogo);
        tvComerceName.setText(commerce.getName());
        tvAddress.setText(commerce.getAddress());
        tvAtention.setText(commerce.getAtention());
        tvPhoneNumber.setText(commerce.getPhoneNumber());
        showMarkInMap(new LatLng(commerce.getLatitude(), commerce.getLongitude()), commerce.getBusinessName());
    }

    private void showMarkInMap(LatLng latLng, String title) {
        googleMap.clear();
        if(latLng.latitude==0&&latLng.longitude==0) {
            latLng = SodexoApplication.clientLocation;
            googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
        }else
            googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    private void initializeMap() {
        SupportMapFragment mapFragment = SupportMapFragment.newInstance(createGoogleMapOptions());
        mapFragment.getMapAsync(this);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_map, mapFragment).commit();
    }

    private GoogleMapOptions createGoogleMapOptions() {
        GoogleMapOptions options = new GoogleMapOptions();
        options.compassEnabled(false);
        options.zoomControlsEnabled(false);
        options.rotateGesturesEnabled(false);
        options.tiltGesturesEnabled(false);
        options.maxZoomPreference(17.0f);
        options.minZoomPreference(10.0f);
        return options;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (getArguments() != null) {
            showDataCommerce((CommerceEntity) getArguments().getParcelable(COMMERCE));
        }
    }

    @OnClick(R.id.iv_wishlist)
    public void addToWishList() {
        if (getArguments() != null)
            if (getArguments().getBoolean(IS_WISHLIST, false)) {
                AlertUtil.showMessageAcceptNegative(getContext(), null, "¿Deseas eliminar este comercio del wishlist?", "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.deleteLocalToWishlist(commerceEntity);
                        dialogInterface.dismiss();
                    }
                }, "No", null);
            } else {
                AlertUtil.showMessageAcceptNegative(getContext(), null, "¿Deseas agregar este comercio al wishlist?", "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.addLocalToWishlist(commerceEntity);
                        dialogInterface.dismiss();
                    }
                }, "No", null);
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
        AlertUtil.showMessageAccept(getContext(), "", "Se ha guardado el comercio correctamente", "Aceptar", null);
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
