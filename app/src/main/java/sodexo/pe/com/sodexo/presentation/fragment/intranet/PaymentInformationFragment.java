package sodexo.pe.com.sodexo.presentation.fragment.intranet;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.assets.UbigeoDataStore;
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.adapter.NumberCardAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.UbigeoAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.ViewCreditView;
import sodexo.pe.com.sodexo.presentation.presenter.ViewCreditPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.ViewCreditPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;


public class PaymentInformationFragment extends Fragment implements ViewCreditView {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.sp_delivery_place)
    Spinner spDeliveryPlace;

    @BindView(R.id.ll_payment_information_location)
    LinearLayout llPaymentInformationLocation;

    @BindView(R.id.sp_regions)
    Spinner spRegions;

    @BindView(R.id.sp_provinces)
    Spinner spProvinces;

    @BindView(R.id.sp_district)
    Spinner spDistricts;

    private ViewCreditPresenter presenter;
    private ProgressCustomDialog progressCustomDialog;
    private MainView mainView;

    private UbigeoEntityData ubigeoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_information, container, false);
        ButterKnife.bind(this, view);
        return view;
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
        presenter = new ViewCreditPresenterImplement(this);
        progressCustomDialog = new ProgressCustomDialog();
        presenter.getNumberCards();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateDeliveryPlace();
        populateDepartment();
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
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

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }

    @Override
    public void populateSpinner(final List<CardEntity> list) {

    }

    @Override
    public void showCardDetail(CardDetailEntity cardDetail) {


    }

    public void populateDeliveryPlace(){
        List<CardEntity> itemList = new ArrayList<>();

        itemList.add(new CardEntity("1","Trabajo"));
        itemList.add(new CardEntity("2","Casa"));
        itemList.add(new CardEntity("3","Sodexo"));
        itemList.add(new CardEntity("4","Otro"));

        NumberCardAdapter deliveryPlaceAdapter = new NumberCardAdapter(getContext());
        deliveryPlaceAdapter.addCards(itemList);
        spDeliveryPlace.setAdapter(deliveryPlaceAdapter);
        spDeliveryPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 2:
                        case 5:
                            llPaymentInformationLocation.setVisibility(View.VISIBLE);
                            break;
                        default:
                            llPaymentInformationLocation.setVisibility(View.GONE);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btn_next)
    public void next() {
        mainView.openPaymentInformationSummary();
    }

    private static class StringWithTag {
        public String string;
        public Object tag;

        public StringWithTag(String string, Object tag) {
            this.string = string;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    private void populateDepartment() {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllDepartments());
        spRegions.setAdapter(adapter);
        spRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateProvince(UbigeoDataStore.getAllDepartments().get(i).getDepartment());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateProvince(final String department) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllProvinces(department));
        spProvinces.setAdapter(adapter);
        spProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateDistrict(department, UbigeoDataStore.getAllProvinces(department).get(i).getProvince());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateDistrict(final String department, final String province) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllDistricts(department, province));
        spDistricts.setAdapter(adapter);
        spDistricts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubigeoData = UbigeoDataStore.getAllDistricts(department, province).get(i);
                Log.wtf("HOLLAA","EL UBIGEODATA -> " +ubigeoData.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

