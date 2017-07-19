package sodexo.pe.com.sodexo.presentation.fragment.menu;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.LocalFilterEntity;
import sodexo.pe.com.sodexo.domain.entity.OptionEntity;
import sodexo.pe.com.sodexo.domain.entity.PlaceEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.adapter.PlaceAutocompleteAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.RVOptionAdapter;
import sodexo.pe.com.sodexo.presentation.custom.PlacesAutocomplete;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.dialog.UbigeoDialogFragment;
import sodexo.pe.com.sodexo.presentation.interfaces.MainFView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.OnClickOptionsMain;
import sodexo.pe.com.sodexo.presentation.presenter.MainFPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.MainFPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class MainFragment extends MenuBaseFragment implements OnClickOptionsMain, MainFView, OnMapReadyCallback {
    public static final String DISTANCE = "distance";
    public static final String STARS = "stars";
    public static final String CATEGORY_ID = "category";
    @BindView(R.id.rv_main_options)
    RecyclerView rvMainOptions;
    @BindView(R.id.place_autocomplete)
    PlacesAutocomplete placeAutocomplete;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.iv_position)
    ImageView ivPosition;
    @BindView(R.id.fl_main_commerce)
    FrameLayout flMainCommerce;
    @BindView(R.id.ll_main_option_map)
    LinearLayout llMainOptionMap;
    private boolean initializeMap = true;

    private LatLng latLngPosition;
    private ProgressCustomDialog progressCustomDialog;
    private GoogleMap googleMap;
    private MainFPresenter presenter;
    private List<LocalFilterEntity> list;
    private HashMap<Marker, LocalFilterEntity> markersMap;
    private final AdapterView.OnItemClickListener adapterOnClickOrigin = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            PlaceEntity place = (PlaceEntity) adapterView.getItemAtPosition(position);
            placeAutocomplete.setText(place.getAddress());
            latLngPosition = new LatLng(place.getLatitude(), place.getLongitude());
            presenter.showFilterLocal(latLngPosition, 0);
        }
    };
    private MainView mainView;
    private UbigeoEntityData ubigeo;

    public void setInitializeMap(boolean initializeMap) {
        this.initializeMap = initializeMap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialAutocomplete();
        initializeMap();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        progressCustomDialog = new ProgressCustomDialog();
        Log.d("cycle","view created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("cycle", "onStart");
        presenter = new MainFPresenterImplement(this);
        presenter.populateFilterOption();
        if (getArguments() != null) {
            presenter.getCommercesByRouletteOptions(getArguments().getInt(DISTANCE), getArguments().getInt(CATEGORY_ID), getArguments().getInt(STARS));
        }
    }

    private void initialAutocomplete() {
        placeAutocomplete.setThreshold(3);
        placeAutocomplete.setAdapter(new PlaceAutocompleteAdapter(getContext()));
        placeAutocomplete.setOnItemClickListener(adapterOnClickOrigin);
        placeAutocomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ivCancel.setVisibility(charSequence.length() >= 3 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    private void initializeMap() {
        SupportMapFragment mapFragment = SupportMapFragment.newInstance(createGoogleMapOptions());
        mapFragment.getMapAsync(this);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_map, mapFragment).commit();
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
    public void clickListener(int id) {
        if (list != null) {
            if (list.size() <= 0) {
                showMessageError("No hay comercios cerca a la ubicación indicada.");
            } else {
                if (presenter != null) {
                    if (latLngPosition == null) {
                        if(ubigeo!=null)
                            presenter.showFilterLocalByUbigeo(ubigeo, id);
                        else {
                            if(SodexoApplication.clientLocation!=null)
                                presenter.showFilterLocal(SodexoApplication.clientLocation, id);
                        }
                    } else {
                        presenter.showFilterLocal(latLngPosition, id);
                    }
                } else {
                    showMessageError("Debes buscar una dirección para obtener la lista de comercios.");
                }
            }
        } else {
            showMessageError("Debes buscar una dirección para obtener la lista de comercios.");
        }


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.googleMap.getUiSettings().setMapToolbarEnabled(false);
        this.googleMap.getUiSettings().setCompassEnabled(false);
        if(initializeMap) {
            if(SodexoApplication.clientLocation!=null)
                presenter.showFilterLocal(SodexoApplication.clientLocation, 0);
        }else{
            showLocalFilterInMap(this.list);
        }
        //this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SodexoApplication.clientLocation, 250));
        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LocalFilterEntity localFilterEntity = markersMap.get(marker);
                if (localFilterEntity != null) {
                    CommerceEntity entity = new CommerceEntity();
                    entity.setName(localFilterEntity.getName());
                    entity.setLatitude(localFilterEntity.getLatitude());
                    entity.setLogo(localFilterEntity.getLogo());
                    entity.setLongitude(localFilterEntity.getLongitude());
                    entity.setBanner(localFilterEntity.getBanner());
                    entity.setAddress(localFilterEntity.getAddress());
                    entity.setAtention(localFilterEntity.getAtention());
                    entity.setPhoneNumber(localFilterEntity.getPhoneNumber());
                    mainView.openCommerce(entity, false,true);
                }
                return true;
            }
        });
    }

    @Override
    public void populateOptions(List<OptionEntity> list) {
        RVOptionAdapter adapter = new RVOptionAdapter(getContext(), this);
        adapter.addOptionList(list);
        rvMainOptions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMainOptions.setAdapter(adapter);
    }

    @Override
    public void showMessageError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(SodexoApplication.clientLocation.latitude, SodexoApplication.clientLocation.longitude), 250));
    }

    @Override
    public void showLocalFilterInMap(List<LocalFilterEntity> list) {
        googleMap.clear();
        markersMap = new HashMap<>();
        this.list = list;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LocalFilterEntity local : list) {
            LatLng latLng = new LatLng(local.getLatitude(), local.getLongitude());
            Marker marker = googleMap.addMarker(new MarkerOptions().title(local.getName()).position(latLng));
            builder.include(latLng);
            markersMap.put(marker, local);
        }
        builder.include(SodexoApplication.clientLocation);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 200));
    }

    @Override
    public void showLoading() {
        if (progressCustomDialog != null) {
            progressCustomDialog.show(getActivity().getSupportFragmentManager(), ProgressCustomDialog.class.getName());
        }
    }

    @Override
    public void hideLoading() {
        if (progressCustomDialog != null) {
            progressCustomDialog.dismissAllowingStateLoss();
        }
    }

    @OnClick(R.id.iv_position)
    public void showMyLocation() {
        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googleMap.getMyLocation().getLatitude(), googleMap.getMyLocation().getLongitude()), 250));

    }

    @OnClick(R.id.iv_cancel)
    public void clearAutocomplete() {
        placeAutocomplete.setText("");
        latLngPosition = null;
    }

    @OnClick({R.id.btn_show_map, R.id.btn_show_commerce, R.id.btn_ubigeo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_map:
                if (flMainCommerce.getVisibility() == View.VISIBLE) {
                    flMainCommerce.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.btn_show_commerce:
                if (list != null) {
                    presenter.showCommercesFilter(list);
                }
                break;
            case R.id.btn_ubigeo:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                UbigeoDialogFragment dialogFragment = new UbigeoDialogFragment();
                dialogFragment.setTargetFragment(this, 1000);
                dialogFragment.show(fm, UbigeoDialogFragment.class.getName());
                break;
        }
    }

    @Override
    public void showCommerces(Bundle bundle) {
        flMainCommerce.setVisibility(View.VISIBLE);
        Fragment fragment = Fragment.instantiate(getActivity(), CommerceFragment.class.getName(), bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_commerce, fragment, CommerceFragment.COMMERCES).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            UbigeoEntityData ubigeo = (UbigeoEntityData) data.getParcelableExtra("ubigeo");
            this.ubigeo = ubigeo;
            presenter.getCommercesByUbigeo(ubigeo);
            clearAutocomplete();
        }
    }
}
