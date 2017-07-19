package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.assets.UbigeoDataStore;
import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.CargoEntity;
import sodexo.pe.com.sodexo.domain.entity.SexEntity;
import sodexo.pe.com.sodexo.domain.entity.UserEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.adapter.CargoAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.SexAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.UbigeoAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.EditProfileView;
import sodexo.pe.com.sodexo.presentation.presenter.EditProfilePresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.EditProfilePresenterImplement;

public class EditProfileFragment extends Fragment implements EditProfileView {

    @BindView(R.id.et_last_name)
    TextView etLastName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_ruc)
    TextView tvRuc;
    @BindView(R.id.et_middle_name)
    TextView etMiddleName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.t_cellphone)
    EditText tCellphone;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.sp_department)
    Spinner spDepartment;
    @BindView(R.id.sp_province)
    Spinner spProvince;
    @BindView(R.id.sp_district)
    Spinner spDistrict;
    @BindView(R.id.tv_dni)
    TextView etDni;
    @BindView(R.id.sp_sex)
    Spinner spSex;
    @BindView(R.id.sp_cargo)
    Spinner spCargo;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btn_clean)
    Button btnClean;
    @BindView(R.id.btn_save)
    Button btnSave;

    private UbigeoEntityData ubigeoData;
    private SexEntity sexEntity;
    private CargoEntity cargoEntity;
    private ProgressCustomDialog dialog;
    private EditProfilePresenter presenter;
    private List<CargoEntity> listCargo;
    private List<SexEntity> listSex;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        populateDepartment();
        populateCargo();
        populateSex();
        dialog = new ProgressCustomDialog();
        presenter = new EditProfilePresenterImplement(this);
        presenter.getUserInfo();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    private void populateCargo() {
        listCargo = new ArrayList<>();
        listCargo.add(new CargoEntity("01", "Gerente General"));
        listCargo.add(new CargoEntity("02", "Gerente de Operaciones"));
        listCargo.add(new CargoEntity("03", "Gerente de RR.HH."));
        listCargo.add(new CargoEntity("04", "Gerente de Sistemas"));
        listCargo.add(new CargoEntity("05", "Asistente"));
        listCargo.add(new CargoEntity("6", "Secretaria"));
        listCargo.add(new CargoEntity("7", "Jefe de RR.HH."));
        listCargo.add(new CargoEntity("8", "Gerente de Logistica"));
        listCargo.add(new CargoEntity("9", "Supervisor/Coordinador"));
        listCargo.add(new CargoEntity("10", "Ejecutivo de Ventas"));
        listCargo.add(new CargoEntity("11", "Jefe de Sistemas"));
        listCargo.add(new CargoEntity("12", "Gerente Comercial"));
        listCargo.add(new CargoEntity("13", "Recepcionista"));
        listCargo.add(new CargoEntity("14", "Jefe de Logistica"));
        listCargo.add(new CargoEntity("15", "Administrador"));
        listCargo.add(new CargoEntity("16", "Obrero"));
        listCargo.add(new CargoEntity("17", "Practicante"));
        listCargo.add(new CargoEntity("18", "Asistenta Social"));
        final CargoAdapter adapter = new CargoAdapter(getActivity());
        adapter.addCards(listCargo);
        spCargo.setAdapter(adapter);
        spCargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cargoEntity = listCargo.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateSex() {
        listSex = new ArrayList<>();
        listSex.add(new SexEntity("M", "MASCULINO"));
        listSex.add(new SexEntity("F", "FEMENINO"));
        SexAdapter adapter = new SexAdapter(getActivity());
        adapter.addCards(listSex);
        spSex.setAdapter(adapter);
        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sexEntity = listSex.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateDepartment() {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllDepartments());
        spDepartment.setAdapter(adapter);
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spProvince.setAdapter(adapter);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spDistrict.setAdapter(adapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubigeoData = UbigeoDataStore.getAllDistricts(department, province).get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showloading() {
        if (dialog != null) {
            dialog.show(getActivity().getSupportFragmentManager(), ProgressCustomDialog.class.getName());
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismissAllowingStateLoss();
        }
    }

    @Override
    public void showError(String error) {
        AlertUtil.showMessageAccept(getContext(), "", error, "Aceptar", null);
    }

    @Override
    public void showdata(UserEntity user) {
        String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
        LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
        tvRuc.setText(data.getRuc());
        etDni.setText(data.getDni());
        tvName.setText(user.getName());
        etLastName.setText(user.getLastName());
        etMiddleName.setText(user.getMiddleName());
        etEmail.setText(user.getEmail());
        spCargo.setSelection(((CargoAdapter)spCargo.getAdapter()).getPosition(new CargoEntity(user.getCargo(), "")));
        spDepartment.setSelection(((UbigeoAdapter)spDepartment.getAdapter()).getPosition(user.getDepartment(), "00", "00"));
        if(spProvince.getAdapter()!=null)
            spProvince.setSelection(((UbigeoAdapter)spProvince.getAdapter()).getPosition(user.getDepartment(), user.getProvincie(), "00"));
        if(spDistrict.getAdapter()!=null)
            spDistrict.setSelection(((UbigeoAdapter)spDistrict.getAdapter()).getPosition(user.getDepartment(), user.getProvincie(), user.getDistrict()));
        spSex.setSelection(((SexAdapter)spSex.getAdapter()).getPosition(user.getSex()));
        etPhone.setText(user.getPhone());
        tCellphone.setText(user.getCellPhone());
        etAddress.setText(user.getAddress());
    }

    @OnClick(R.id.btn_save)
    public void save() {
        if((!TextUtils.isEmpty(etEmail.getText().toString()))&&(!TextUtils.isEmpty(etPhone.getText().toString()))&&
                (!TextUtils.isEmpty(tCellphone.getText().toString()))&&(!TextUtils.isEmpty(etAddress.getText().toString()))){
            if((isValidMobile(etPhone.getText().toString()))&&(isValidMobile(tCellphone.getText().toString()))){
                presenter.updateInfo(etAddress.getText().toString(),
                        ubigeoData.getDepartment(),
                        ubigeoData.getProvince(),
                        ubigeoData.getDistrict(),
                        etEmail.getText().toString(),
                        etPhone.getText().toString(),
                        tCellphone.getText().toString(),
                        cargoEntity.getId(),
                        sexEntity.getId());
            }
            else{
                showError("Por favor ingrese numeros de teléfono válidos");
            }

        }
        else{
            showError("Por favor complete los campos obligatorios");
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }

    @OnClick(R.id.btn_clean)
    public void cleanForm() {
        etEmail.setText("");
        etPhone.setText("");
        tCellphone.setText("");
        etAddress.setText("");
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches() && phone.length()>=7;
    }
}
