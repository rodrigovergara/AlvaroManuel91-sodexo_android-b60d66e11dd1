package sodexo.pe.com.sodexo.presentation.fragment.intranet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.assets.UbigeoDataStore;
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
import sodexo.pe.com.sodexo.domain.entity.ShippingAddressEntity;
import sodexo.pe.com.sodexo.domain.entity.StringWithTag;
import sodexo.pe.com.sodexo.presentation.adapter.SimpleObjectAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.UbigeoAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.DeliveryInformationView;
import sodexo.pe.com.sodexo.presentation.presenter.DeliveryInformationPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.DeliveryInformationPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;


public class DeliveryInformationFragment extends Fragment implements DeliveryInformationView {

    public static final String CARD_NUMBER = "card_number";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.sp_delivery_place)
    Spinner spDeliveryPlace;

    @BindView(R.id.ll_delivery_information_location)
    LinearLayout llPaymentInformationLocation;

    @BindView(R.id.sp_department)
    Spinner spDepartment;

    @BindView(R.id.sp_province)
    Spinner spProvince;

    @BindView(R.id.sp_district)
    Spinner spDistrict;

    @BindView(R.id.et_address)
    EditText etAddress;

    @BindView(R.id.et_contact_name)
    EditText etContactName;

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;

    @BindView(R.id.et_email)
    EditText etEmail;

    private DeliveryInformationPresenter deliveryInformationPresenter;
    private ProgressCustomDialog progressCustomDialog;
    private MainView mainView;

    private UbigeoEntityData ubigeoData;
    private String cardNumber;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llPaymentInformationLocation.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_information, container, false);
        ButterKnife.bind(this, view);
        deliveryInformationPresenter = new DeliveryInformationPresenterImplement(this);
        if (getArguments() != null) {
            cardNumber = getArguments().getString(CARD_NUMBER);
        }
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
        progressCustomDialog = new ProgressCustomDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateDeliveryPlace();
        populateDepartment(null,null,null);
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

    public void populateDeliveryPlace() {
        final List<StringWithTag> itemList = new ArrayList<>();

        //el adapter le agrega en la posicion 0 el "seleccione "
        itemList.add(new StringWithTag("1", "Trabajo"));
        itemList.add(new StringWithTag("2", "Casa"));
        itemList.add(new StringWithTag("3", "Sodexo"));
        itemList.add(new StringWithTag("4", "Otro"));

        SimpleObjectAdapter deliveryPlaceAdapter = new SimpleObjectAdapter(getContext(), itemList);
        spDeliveryPlace.setAdapter(deliveryPlaceAdapter);
        spDeliveryPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.wtf("EL POSITION ", " -> " + position + " - " + id + " - " + cardNumber);

                if (position == 1 || position == 3) {
                    StringWithTag object = itemList.get(position - 1);
                    Log.wtf("EL OBJECT : ", " -> " + object.toString());
                    if (cardNumber != null)
                        deliveryInformationPresenter.getShippingAddress(cardNumber, object.getString());
                } else {
                    Log.wtf("EN EL DEFAULT: ", " -> ");
                    spDepartment.setEnabled(true);
                    spProvince.setEnabled(true);
                    spDistrict.setEnabled(true);
                    etAddress.setEnabled(true);
                    spDepartment.setSelection(0);
                    spProvince.setSelection(0);
                    spDistrict.setSelection(0);
                    etAddress.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btn_next)
    public void next() {
        if (spDeliveryPlace.getSelectedItemPosition() == 0)
            showError("Por favor seleccione un lugar de entrega.");
        else if (!TextUtils.isEmpty(etAddress.getText().toString()) && !TextUtils.isEmpty(etContactName.getText().toString()) && !TextUtils.isEmpty(etPhoneNumber.getText().toString())
                && !TextUtils.isEmpty(etEmail.getText().toString())) {
            ReplacementCardEntity replacementCardEntity = new ReplacementCardEntity();
            replacementCardEntity.setAddress1(etAddress.getText().toString());
            replacementCardEntity.setCardNumber(cardNumber);
            replacementCardEntity.setContactName(etContactName.getText().toString());
            replacementCardEntity.setDeliveryPlace(spDeliveryPlace.getSelectedItemPosition() + "");
            replacementCardEntity.setDepartmentId(ubigeoData.getDepartment());
            replacementCardEntity.setProvinceId(ubigeoData.getProvince());
            replacementCardEntity.setDistrictId(ubigeoData.getDistrict());
            replacementCardEntity.setPhoneNumber(etPhoneNumber.getText().toString());
            replacementCardEntity.setEmail(etEmail.getText().toString());

            mainView.openPaymentInformationSummary(replacementCardEntity);
        } else
            showError("Por favor complete los campos obligatorios");
    }

    private void populateDepartment(final String departmentId,final String provinceId,final String districtId) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.setList(UbigeoDataStore.getAllDepartments());
        spDepartment.setAdapter(adapter);
        if(departmentId!= null)
            spDepartment.setSelection(((UbigeoAdapter) spDepartment.getAdapter()).getPosition(departmentId, "00", "00"));
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateProvince(UbigeoDataStore.getAllDepartments().get(i).getDepartment(),provinceId,districtId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateProvince(final String departmentId,String provinceId,final String districtId) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.setList(UbigeoDataStore.getAllProvinces(departmentId));
        spProvince.setAdapter(adapter);
        if(provinceId!=null)
            spProvince.setSelection(((UbigeoAdapter) spProvince.getAdapter()).getPosition(departmentId, provinceId, "00"));
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateDistrict(departmentId, UbigeoDataStore.getAllProvinces(departmentId).get(i).getProvince(),districtId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateDistrict(final String departmentId, final String provinceId, String districtId) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.setList(UbigeoDataStore.getAllDistricts(departmentId, provinceId));
        spDistrict.setAdapter(adapter);
        if(districtId != null)
            spDistrict.setSelection(((UbigeoAdapter) spDistrict.getAdapter()).getPosition(departmentId, provinceId, districtId));

        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubigeoData = UbigeoDataStore.getAllDistricts(departmentId, provinceId).get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onGetShippingAddressResults(ShippingAddressEntity shippingAddressEntity) {
        String departmentId = shippingAddressEntity.getDistrictId().substring(0, 2);
        String provinceId = shippingAddressEntity.getDistrictId().substring(2, 4);
        String districtId = shippingAddressEntity.getDistrictId().substring(4, 6);

        populateDepartment(departmentId,provinceId,districtId);

        etAddress.setText(shippingAddressEntity.getAddress());

        spDepartment.setEnabled(false);
        spProvince.setEnabled(false);
        spDistrict.setEnabled(false);
        etAddress.setEnabled(false);
    }
}

